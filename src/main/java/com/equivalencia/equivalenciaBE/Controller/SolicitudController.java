package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.AlumnoModel;
import com.equivalencia.equivalenciaBE.Model.AlumnoSolicitud;
import com.equivalencia.equivalenciaBE.Model.AsignaturaEquivalente;
import com.equivalencia.equivalenciaBE.Model.AsignaturasUngs;
import com.equivalencia.equivalenciaBE.Model.CarreraModel;
import com.equivalencia.equivalenciaBE.Model.CodigoAlumno;
import com.equivalencia.equivalenciaBE.Model.MateriasDocente;
import com.equivalencia.equivalenciaBE.Model.SolicitudMateriaAlumno;
import com.equivalencia.equivalenciaBE.Model.SolicitudModel;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;
import com.equivalencia.equivalenciaBE.Service.SolicitudService;
import com.equivalencia.equivalenciaBE.Utilities.Codificador;
import com.equivalencia.equivalenciaBE.Utilities.EnviadorMail;
import com.equivalencia.equivalenciaBE.Utilities.EstadoSolicitud;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SolicitudController {

	@Autowired
	protected AlumnoController alumnoController;
	
	@Autowired
	protected SolicitudService solicitudService;
	
	@Autowired
	protected FolioController folioController;
	
	@Autowired
	protected ComentarioController comentarioController;
	@Autowired
	protected CarreraController carreraController;
	
	@Autowired
	protected CertificadoController certificadoController;

	@Autowired
	protected PlanController planController;
	@Autowired
	protected MateriaController materiaController;
	
	protected ObjectMapper mapper;
	
	protected EnviadorMail enviadorMail;
	
	
	@RequestMapping(value = "/solicitud", method = RequestMethod.POST)
	public String addSolicitud(@RequestBody String solicitudJson) throws JsonParseException, JsonMappingException, IOException{
			this.mapper= new ObjectMapper();
			
			this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			
	        SolicitudPost solicitud=mapper.readValue(solicitudJson, SolicitudPost.class);
	        Alumno alumno = solicitud.getAlumno();
	        Carrera carrera= this.carreraController.getCarreraPorNombre(solicitud.getCarrera());
	        
	        if(!this.alumnoController.existe(alumno)) {
	        
	        	Folio folio=this.folioController.crearFolio();
	        	folio=this.folioController.guardarFolio(folio);
	        
	        	//guardo certificados
	        	Certificado certificado= solicitud.getCertificado();
	        	certificado = this.certificadoController.save(certificado);
	        
	        	alumno.setIdCertificados(certificado.getId());
	        	alumno.setIdCarrera(carrera.getId());
	        	alumno=this.alumnoController.saveAlumno(alumno);
	        
	        	List<SolicitudModel> solicitudes = solicitud.getSolicitudesModel();
	        
	        
	        	for(SolicitudModel solicitudModel: solicitudes) {
	        		List<Long> idsOfrecimiento= new ArrayList<Long>();
	        		System.out.println(solicitudModel.getmateriaUngs()+": ");
	        		for(AsignaturaEquivalente asignaturaEquivalente: solicitudModel.getAsignaturaEquivalente()) {
	        			
	        			PlanMateriaOfrecida plan= new PlanMateriaOfrecida();
	        			plan.setDocumentacion(asignaturaEquivalente.getDocumentacion());
	        			plan= this.planController.saveOfrecida(plan);
	        			
	        			SolicitudOfrecimiento solicitudOfrecimiento = new SolicitudOfrecimiento();
	        			solicitudOfrecimiento.setAnioAprobacion(asignaturaEquivalente.getAnioAprobacion());
	        			solicitudOfrecimiento.setCargaHoraria(asignaturaEquivalente.getCargaHoraria());
	        			solicitudOfrecimiento.setNombre(asignaturaEquivalente.getNombre());
	        			solicitudOfrecimiento.setUniversidad(asignaturaEquivalente.getUniversidad());
	        			solicitudOfrecimiento.setIdPlan(plan.getId());
	        			
	        			System.out.println(asignaturaEquivalente.getNombre()+", ");
	        			
	        			idsOfrecimiento.add(this.solicitudService.save(solicitudOfrecimiento).getId());
	        			
	        		}
	        
	        	 	Comentario comentario = this.comentarioController.crearComentario();
	    	        
		        	Solicitud soli= new Solicitud();
		        	soli.setIdAlumno(alumno.getId());
		        	soli.setIdFolio(folio.getId());
		        	soli.setComentario(comentario.getId());
		        	soli.setEstado(EstadoSolicitud.En_espera);
		        	soli= this.solicitudService.save(soli);
		        	Materia materia=this.materiaController.getMateria(solicitudModel.getmateriaUngs());
		        	
		        	this.materiaController.SolicitudHasMateriasUngs(materia.getId(),soli.getId());
		        	
		        	this.solicitudService.SolicitudHasMateriasOfrecidas(soli.getId(),idsOfrecimiento);
			        
	        		
	        	}
	        	System.out.println(alumno.getEmail()+ " "+folio.getCodigo());
	        	this.enviadorMail.enviarConGMail(alumno.getEmail(), folio.getCodigo());
	        	return this.mapper.writeValueAsString(folio.getCodigo());
	        }
	        
	       else {
	    	   //respues a que ya existe folio
	    	   return this.mapper.writeValueAsString(0);
	        }
	}
	
	@RequestMapping(value = "/getSolicitud", method = RequestMethod.POST)
	public String getSolicitudPorCodigo(@RequestBody String stringJson) throws JsonProcessingException {
		try {
			this.mapper= new ObjectMapper();		
			
			CodigoAlumno codigo=this.mapper.readValue(stringJson,CodigoAlumno.class);
			
			Folio folio= this.folioController.getOne(codigo.getCodigo());
			
			AlumnoSolicitud alumnoSolicitud=new AlumnoSolicitud();
			SolicitudPost ret= new SolicitudPost();
			
			List<Solicitud> solicitudes = this.solicitudService.findAll(folio.getId());
			List<SolicitudModel> solicitudesModel = new ArrayList<SolicitudModel>();
			
			alumnoSolicitud=guardarAlumnoSolicitud(solicitudes.get(0).getIdAlumno());
			
			ret.setAlumno(alumnoSolicitud);
			
			
			for(Solicitud solicitud: solicitudes) {	
				SolicitudModel solicitudModel= new SolicitudModel();
				solicitudModel.setmateriaUngs(this.solicitudService.findMateria(solicitud.getId()));
				solicitudModel.setAsignaturaEquivalente(cargarAsignatura(this.solicitudService.findMateriaOfrecimiento(solicitud.getId())));
				solicitudModel.setAlumno(alumnoSolicitud);
				solicitudModel.setEstado(solicitud.getEstado());
				solicitudModel.setComentario(this.comentarioController.findComentario(solicitud.getId()));
				solicitudesModel.add(solicitudModel);
		
			}
			
			ret.setsolicitudesModel(solicitudesModel);
			return this.mapper.writeValueAsString(ret);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.NOT_FOUND.value(),"No encontrado"));
	}
	
	@RequestMapping(value = "/getPorMateriasDocente", method = RequestMethod.POST)
	public String getSolicitudesMateria(@RequestBody String solicitudJson) throws JsonParseException, JsonMappingException, IOException {
		this.mapper= new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		MateriasDocente materia= this.mapper.readValue(solicitudJson, MateriasDocente.class);

		List<SolicitudMateriaAlumno> ret = new ArrayList<SolicitudMateriaAlumno>();
		
		for(String nombreMateria: materia.getMaterias()) {
			Materia mat = this.materiaController.getMateria(nombreMateria);
			List<SolicitudHasMateriasUngs> solicitudHas = this.materiaController.getSolicitudHasMateriaUngs(mat.getId());
			
		for(SolicitudHasMateriasUngs solicitudHasMateria: solicitudHas) {
				Solicitud soli= this.solicitudService.getOne(solicitudHasMateria.getSegundaClave());
				
				
				List<SolicitudHasMateria> materiasOfrecidas= new ArrayList<SolicitudHasMateria>();
				materiasOfrecidas=this.materiaController.getSolicitudHasMateria(soli.getId());
				
				SolicitudMateriaAlumno solicitudMateriaAlumno= new SolicitudMateriaAlumno();
				solicitudMateriaAlumno.setAlumno(guardarModel(soli.getIdAlumno()));
				
				List<AsignaturasUngs> asignaturasUngs=new ArrayList<AsignaturasUngs>();
				
				AsignaturasUngs asignaturaUngs=new AsignaturasUngs();
				
				asignaturaUngs.setMateriaUngs(nombreMateria);
			
				//solicitudMateriaAlumno.setComentario(this.comentarioController.findComentario(soli.getId()));
				
				List<AsignaturaEquivalente> ofrecimientos= new ArrayList<AsignaturaEquivalente>();
				
				for(SolicitudHasMateria materiaOfrecida :materiasOfrecidas) {
					
					AsignaturaEquivalente asignatura = new AsignaturaEquivalente();
					
					SolicitudOfrecimiento solicitudOfrecimiento= this.materiaController.findMateriaOfrecimiento(materiaOfrecida.getSegundaClave());
					
					asignatura.setAnioAprobacion(solicitudOfrecimiento.getAnioAprobacion());
					asignatura.setCargaHoraria(solicitudOfrecimiento.getCargaHoraria());
					asignatura.setNombre(solicitudOfrecimiento.getNombre());
					asignatura.setUniversidad(solicitudOfrecimiento.getUniversidad());
					asignatura.setDocumentacion(this.planController.getOneOfrecida(solicitudOfrecimiento.getIdPlan()));
				
					ofrecimientos.add(asignatura);
					
					
				}
				asignaturaUngs.setEquivalencias(ofrecimientos);
				asignaturasUngs.add(asignaturaUngs);
				solicitudMateriaAlumno.setAsignaturaUngs(asignaturasUngs);
				ret.add(solicitudMateriaAlumno);
				
			}
		}
		
		return this.mapper.writeValueAsString(ret);
		
	}
	
	public AlumnoSolicitud guardarAlumnoSolicitud(long id) {
		Alumno alumno = this.alumnoController.getOne(id);
		AlumnoSolicitud alumnoSolicitud=new AlumnoSolicitud();
		
		alumnoSolicitud.setNombre(alumno.getNombre());
		alumnoSolicitud.setApellido(alumno.getApellido());
		alumnoSolicitud.setEmail(alumno.getEmail());
		alumnoSolicitud.setDni(alumno.getDni());
		alumnoSolicitud.setTelefono(alumno.getTelefono());
		alumnoSolicitud.setLegajo(alumno.getLegajo());
		Carrera carrera=new Carrera();
		carrera=this.carreraController.getOne(alumno.getIdCarrera());
		
		alumnoSolicitud.setCarrera(carrera.getNombre());
		Certificado certificado=this.certificadoController.getOne(alumno.getIdCertificados());
		alumnoSolicitud.setConstancia(certificado.getConstancia());
		alumnoSolicitud.setAnalitico(certificado.getAnalitico());
		
		
		return alumnoSolicitud;
	}
	
	public AlumnoModel guardarModel(long id) {
		Alumno alumno = this.alumnoController.getOne(id);
		AlumnoModel model = new AlumnoModel();
		model.setNombre(alumno.getNombre());
		model.setApellido(alumno.getApellido());
		System.out.println(alumno.getEmail()+" email");
		model.setEmail(alumno.getEmail());
		model.setDni(alumno.getDni());
		model.setTelefono(alumno.getTelefono());
		model.setLegajo(alumno.getLegajo());
		
		Carrera carrera=new Carrera();
		carrera=this.carreraController.getOne(alumno.getIdCarrera());
		model.setCarrera(carrera.getNombre());
		Certificado certificado=this.certificadoController.getOne(alumno.getIdCertificados());
		model.setCertificado(certificado);
		
		return model;
	}
	
	
	
	public List<AsignaturaEquivalente> cargarAsignatura(List<SolicitudOfrecimiento> asignaturas) {
		List<AsignaturaEquivalente> ret = new ArrayList<AsignaturaEquivalente>();
		for(SolicitudOfrecimiento solicitud: asignaturas) {
			AsignaturaEquivalente asignaturaEquivalente = new AsignaturaEquivalente();
			asignaturaEquivalente.setAnioAprobacion(solicitud.getAnioAprobacion());
			asignaturaEquivalente.setCargaHoraria(solicitud.getCargaHoraria());
			asignaturaEquivalente.setNombre(solicitud.getNombre());
			asignaturaEquivalente.setUniversidad(solicitud.getUniversidad());
			asignaturaEquivalente.setDocumentacion(this.planController.getOneOfrecida(solicitud.getIdPlan()));
			ret.add(asignaturaEquivalente);
		}
		
		return ret;
	}
}
