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
import com.equivalencia.equivalenciaBE.Model.AsignaturasUNGS;
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
import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;
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
	protected InstitutoController institutoController;
	
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
	        Alumno alumno = this.getAlumno(solicitud.getAlumno());
	        Carrera carrera= this.carreraController.getCarreraPorNombre(solicitud.getCarrera());
	        
	        if(!this.alumnoController.existe(alumno)) {
	        
	        	Folio folio=this.folioController.crearFolio();
	        	folio=this.folioController.guardarFolio(folio);
	        
	        	//guardo certificados
	        	Certificado certificado= this.getCertificado(solicitud.getAlumno());
	        	certificado = this.certificadoController.save(certificado);
	        
	        	alumno.setIdCertificados(certificado.getId());
	        	alumno.setIdCarrera(carrera.getId());
	        	alumno=this.alumnoController.saveAlumno(alumno);
	        
	        	List<SolicitudModel> solicitudes = solicitud.getSolicitudesModel();
	        
	        
	        	for(SolicitudModel solicitudModel: solicitudes) {
	        		List<Long> idsOfrecimiento= new ArrayList<Long>();
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
	        			
	        			
	        			idsOfrecimiento.add(this.solicitudService.save(solicitudOfrecimiento).getId());
	        			
	        		}
	        		
	        		
	        		//busco  id de materia de la ungss
	        		Materia materia=this.materiaController.getMateriaPorNombre(solicitudModel.getmateriaUngs());
	        		
	        	 	Comentario comentario = this.comentarioController.crearComentario();
	    	        
		        	Solicitud soli= new Solicitud();
		        	soli.setIdAlumno(alumno.getId());
		        	soli.setIdFolio(folio.getId());
		        	soli.setComentario(comentario.getId());
		        	soli.setEstado(EstadoSolicitud.En_espera);
		        	soli= this.solicitudService.save(soli);
		        	
		        	
		        	this.materiaController.SolicitudHasMateriasUngs(materia.getId(),soli.getId());
		        	
		        	this.solicitudService.SolicitudHasMateriasOfrecidas(soli.getId(),idsOfrecimiento);
		        	
		        	//busco los docente que dictan esa materia
	        		this.enviarMailADocentes(this.materiaController.BuscarMateriasDeDocente(materia.getId()));
	        		
	        	}
	        	
	        	this.enviarMailAAlumno(alumno.getEmail(), folio.getCodigo());
	        	return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),folio.getCodigo()));
	        }
	        
	       else {
	    	   //respues a que ya existe folio
	    	   return this.mapper.writeValueAsString(new RestResponse(HttpStatus.CONFLICT.value(),"Email y/o legajo ya registrado"));
	        }
	}
	
	@RequestMapping(value = "/getSolicitud", method = RequestMethod.POST)
	public String getSolicitudPorCodigo(@RequestBody String stringJson) throws JsonProcessingException {
		try {
			this.mapper= new ObjectMapper();		
			
			CodigoAlumno codigo=this.mapper.readValue(stringJson,CodigoAlumno.class);
			if(this.folioController.getOne(codigo.getCodigo()) != null) {
				Folio folio= this.folioController.getOne(codigo.getCodigo());
			
				AlumnoSolicitud alumnoSolicitud=new AlumnoSolicitud();
				SolicitudPost ret= new SolicitudPost();
			
				List<Solicitud> solicitudes = this.solicitudService.findAllPorFolio(folio.getId());
				List<SolicitudModel> solicitudesModel = new ArrayList<SolicitudModel>();
			
				alumnoSolicitud=guardarAlumnoSolicitud(solicitudes.get(0).getIdAlumno());
			
				ret.setAlumnoSolicitud(alumnoSolicitud);
			
			
				for(Solicitud solicitud: solicitudes) {	
					SolicitudModel solicitudModel= new SolicitudModel();
					solicitudModel.setmateriaUngs(this.solicitudService.findMateriaUngs(solicitud.getId()));
					solicitudModel.setAsignaturaEquivalente(cargarAsignatura(this.solicitudService.findMateriaOfrecimiento(solicitud.getId())));
					solicitudModel.setAlumno(alumnoSolicitud);
					solicitudModel.setEstado(solicitud.getEstado());
					solicitudModel.setComentario(this.comentarioController.findComentario(solicitud.getId()).getComentario());
					solicitudesModel.add(solicitudModel);
		
				}
			
				ret.setsolicitudesModel(solicitudesModel);
				return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
			}
			else {
				return this.mapper.writeValueAsString(new RestResponse(HttpStatus.NOT_FOUND.value(),"codigo no existente"));
			}
			
			
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
			Materia mat = this.materiaController.getMateriaPorNombre(nombreMateria);
			List<SolicitudHasMateriasUngs> solicitudHas = this.materiaController.getSolicitudHasMateriaUngs(mat.getId());
			
		for(SolicitudHasMateriasUngs solicitudHasMateria: solicitudHas) {//segunda clave =idmateriaofrecida
				Solicitud soli= this.solicitudService.getSolicitudEnEspera(solicitudHasMateria.getSegundaClave());
				if(soli !=null) {
				
					List<SolicitudHasMateria> materiasOfrecidas= new ArrayList<SolicitudHasMateria>();
					materiasOfrecidas=this.materiaController.getSolicitudHasMateria(soli.getId());
				
					SolicitudMateriaAlumno solicitudMateriaAlumno= new SolicitudMateriaAlumno();
					solicitudMateriaAlumno.setAlumno(guardarModel(soli.getIdAlumno()));
				
					List<AsignaturasUNGS> asignaturasUNGS=new ArrayList<AsignaturasUNGS>();
				
					AsignaturasUNGS asignaturaUNGS=new AsignaturasUNGS();
				
					asignaturaUNGS.setMateriaUngs(nombreMateria);
			
					//solicitudMateriaAlumno.setComentario(this.comentarioController.findComentario(soli.getId()));
				
					List<AsignaturaEquivalente> ofrecimientos= new ArrayList<AsignaturaEquivalente>();
				
					for(SolicitudHasMateria materiaOfrecida :materiasOfrecidas) {
					
						AsignaturaEquivalente asignatura = new AsignaturaEquivalente();
						//id de las materias ofrecida
						SolicitudOfrecimiento solicitudOfrecimiento= this.materiaController.findMateriaOfrecimiento(materiaOfrecida.getSegundaClave());
					
						asignatura.setAnioAprobacion(solicitudOfrecimiento.getAnioAprobacion());
						asignatura.setCargaHoraria(solicitudOfrecimiento.getCargaHoraria());
						asignatura.setNombre(solicitudOfrecimiento.getNombre());
						asignatura.setUniversidad(solicitudOfrecimiento.getUniversidad());
						asignatura.setDocumentacion(this.planController.getOneOfrecida(solicitudOfrecimiento.getIdPlan()));
					
						ofrecimientos.add(asignatura);
						
						
						}
					
					
					asignaturaUNGS.setEquivalencias(ofrecimientos);
					asignaturasUNGS.add(asignaturaUNGS);
					solicitudMateriaAlumno.setAsignaturasUNGS(asignaturasUNGS);
					ret.add(solicitudMateriaAlumno);
					}
					
			}
		}
		
		return this.mapper.writeValueAsString(ret);
		
	}
	
	@RequestMapping(value = "/actualizarSolcitud", method = RequestMethod.POST)
	public String actualizarSolicitud(@RequestBody String stringJson) throws IOException {
		
			this.mapper= new ObjectMapper();		
			
			this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			
	        SolicitudPost solicitud=mapper.readValue(stringJson, SolicitudPost.class);
	        Alumno alumno = this.getAlumno(solicitud.getAlumno());
	        Carrera carrera= this.carreraController.getCarreraPorNombre(solicitud.getCarrera());
	        
	        //buscar alumno
	        Alumno alumn= this.alumnoController.buscarPorEmail(alumno.getEmail());
	        
	        List<SolicitudModel> solicitudes = solicitud.getSolicitudesModel();
	        
	        for(SolicitudModel solicitudModel: solicitudes) {
	        		        			        		
	        	Solicitud soli = this.solicitudService.buscarSolicitudPorAlumnoyMateriaUngs(alumn.getId(),solicitudModel.getmateriaUngs());
	        		  
		        Comentario comentario = this.comentarioController.findComentario(soli.getComentario());
	        	comentario.setComentario(solicitudModel.getComentario());
		        
	        	
	        	this.comentarioController.actualizarComentario(comentario);
	        	
	        	
	        	switch(solicitudModel.getEstado()) {
	        	   	case "aprobado" :
	        	   		soli.setEstado(EstadoSolicitud.Aprobado);
	        	   		break; // optional
	        	   
	        	   case "desaprobado" :
	        		   soli.setEstado(EstadoSolicitud.Desaprobado);
	        	      break; // optional
	        	   
	        	   case "coloquio" :
	        		   soli.setEstado(EstadoSolicitud.Coloquio);
	        	      break; // optional
	        	   	        	      
	        	}
	        	
	        	
		        this.solicitudService.actualizarSolicitud(soli);
		       
	        	}
	        	
	        	return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"actualizado"));
			
	}
	
	
///ASCOOOOOOOOOOOOOO
	
	@RequestMapping(value = "/solicitudesAdmin", method = RequestMethod.POST)
	public String obtenerSoliciutesAdmin(@RequestBody String solicitudJson) throws IOException {

		this.mapper= new ObjectMapper();
		String inst=solicitudJson;
		
		Instituto instituto= this.institutoController.obtenerInstitutoPorNombre(inst);
		List<Alumno> alumnos= this.alumnoController.traerPorInstituto(instituto.getId());
		List<SolicitudPost> ret = new ArrayList<SolicitudPost>();

		for(Alumno alumno: alumnos) {
			AlumnoSolicitud alumnoSoli= this.getAlumnoSoli(alumno);
			List<SolicitudModel> SolicitudesModel=new ArrayList<SolicitudModel>();
			List<Solicitud> solicitudes = this.solicitudService.buscarSolicitudPorAlumno(alumno.getId());
				
			for(Solicitud solicitud : solicitudes) {
				
				SolicitudModel solicitudModel= new SolicitudModel();
				
				List<SolicitudHasMateria> materiasOfrecidas= new ArrayList<SolicitudHasMateria>();
				materiasOfrecidas=this.materiaController.getSolicitudHasMateria(solicitud.getId());
				
				solicitudModel.setmateriaUngs(this.materiaController.buscarPorSolicitud(solicitud.getId()).getNombre());
	
				List<AsignaturaEquivalente> ofrecimientos= new ArrayList<AsignaturaEquivalente>();
				
				//busco las materiasofrecidas
				for(SolicitudHasMateria materiaOfrecida :materiasOfrecidas) {
					
					AsignaturaEquivalente asignatura = new AsignaturaEquivalente();
						//id de las materias ofrecida
					SolicitudOfrecimiento solicitudOfrecimiento= this.materiaController.findMateriaOfrecimiento(materiaOfrecida.getSegundaClave());
					
						asignatura.setAnioAprobacion(solicitudOfrecimiento.getAnioAprobacion());	
						asignatura.setCargaHoraria(solicitudOfrecimiento.getCargaHoraria());
						asignatura.setNombre(solicitudOfrecimiento.getNombre());
						asignatura.setUniversidad(solicitudOfrecimiento.getUniversidad());
						asignatura.setDocumentacion(this.planController.getOneOfrecida(solicitudOfrecimiento.getIdPlan()));
					
						ofrecimientos.add(asignatura);
					}
									
				solicitudModel.setAsignaturaEquivalente(ofrecimientos);
				solicitudModel.setEstado(solicitud.getEstado());

				SolicitudesModel.add(solicitudModel);
			}
			SolicitudPost solicitudPost= new SolicitudPost();
			solicitudPost.setAlumnoSolicitud(alumnoSoli);
			solicitudPost.setsolicitudesModel(SolicitudesModel);
			
			ret.add(solicitudPost);
		}
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));

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
		alumnoSolicitud.setDocumentacion(certificado.getConstancia());
		alumnoSolicitud.setAnalitico(certificado.getAnalitico());
		
		
		return alumnoSolicitud;
	}
	
	public AlumnoModel guardarModel(long id) {
		Alumno alumno = this.alumnoController.getOne(id);
		AlumnoModel model = new AlumnoModel();
		model.setNombre(alumno.getNombre());
		model.setApellido(alumno.getApellido());
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
	



	public AlumnoSolicitud getAlumnoSoli(Alumno alumno) {
		AlumnoSolicitud alumnoSolicitud=new AlumnoSolicitud();
		alumnoSolicitud.setApellido(alumno.getApellido());
		alumnoSolicitud.setDni(alumno.getDni());
		alumnoSolicitud.setEmail(alumno.getEmail());
		alumnoSolicitud.setLegajo(alumno.getLegajo());
		alumnoSolicitud.setNombre(alumno.getNombre());
		alumnoSolicitud.setTelefono(alumno.getNombre());
		Certificado certificado=this.certificadoController.getOne(alumno.getIdCertificados());
		
		
		alumnoSolicitud.setAnalitico(certificado.getAnalitico());
		alumnoSolicitud.setDocumentacion(certificado.getConstancia());
		
		return alumnoSolicitud;
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
	
	public void enviarMailADocentes(List<Docente> docentes) {
		this.enviadorMail=new EnviadorMail();
		System.out.println(docentes.size());
		for(Docente docente : docentes) {
			System.out.println(docente.getNombre()+" "+docente.getMail() );
			this.enviadorMail.enviarADocente(docente.getMail());
		}
		
	}
	public void enviarMailAAlumno(String email, String codigo) {
		this.enviadorMail=new EnviadorMail();
		this.enviadorMail.enviarAAlumno(email, codigo);
	}
	
	public Alumno getAlumno(AlumnoSolicitud alumnoSolicitud) {
		Alumno alumno=new Alumno();
		alumno.setApellido(alumnoSolicitud.getApellido());
		alumno.setDni(alumnoSolicitud.getDni());
		alumno.setEmail(alumnoSolicitud.getEmail());
		alumno.setLegajo(alumnoSolicitud.getLegajo());
		alumno.setNombre(alumnoSolicitud.getNombre());
		alumno.setTelefono(alumnoSolicitud.getNombre());
		alumno.setIdCarrera(0);
		alumno.setIdCertificados(0);
		
		return alumno;
	}
	
	
	public Certificado getCertificado(AlumnoSolicitud alumno) {
		Certificado ret = new Certificado();
		
		ret.setConstancia(alumno.getDocumentacion());
		ret.setAnalitico(alumno.getAnalitico());
		return ret;
		}
	
}
