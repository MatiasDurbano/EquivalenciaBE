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
import com.equivalencia.equivalenciaBE.Model.CarreraModel;
import com.equivalencia.equivalenciaBE.Model.SolicitudModel;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Service.SolicitudService;
import com.equivalencia.equivalenciaBE.Utilities.Codificador;
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
	
	
	protected ObjectMapper mapper;
	
	
	@RequestMapping(value = "/solicitud", method = RequestMethod.POST)
	public String getSolicitud(@RequestBody String solicitudJson) throws JsonParseException, JsonMappingException, IOException{
			this.mapper= new ObjectMapper();
			
			this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	        SolicitudPost solicitud=mapper.readValue(solicitudJson, SolicitudPost.class);
	        
	        Alumno alumno = solicitud.getAlumno();
	        System.out.println(alumno.getLegajo());
	        if(!this.alumnoController.existe(alumno)) {
	        
	        	Folio folio=this.folioController.crearFolio();
	        	folio=this.folioController.guardarFolio(folio);
	        
	        	
	        	alumno.setIdCertificados(1);
	        	alumno.setIdCarrera(1);
	        	alumno=this.alumnoController.saveAlumno(alumno);
	        
	        	List<SolicitudModel> solicitudes = solicitud.getSolicitudesModel();
	        
	        	List<Long> idsOfrecimiento= new ArrayList<Long>();
	        
	        	for(SolicitudModel solicitudModel: solicitudes) {
	        		for(SolicitudOfrecimiento solicitudOfrecimiento: solicitudModel.getSolicitudOfrecimiento()) {
	        			solicitudOfrecimiento.setIdPlan(1);
	        			idsOfrecimiento.add(this.solicitudService.save(solicitudOfrecimiento).getId());
	        		}
	        	}
	        	Comentario comentario = this.comentarioController.crearComentario();
	        
	        	Solicitud soli= new Solicitud();
	        	soli.setIdAlumno(alumno.getId());
	        	soli.setIdFolio(folio.getId());
	        	soli.setComentario(comentario.getId());
	        	soli.setEstado(EstadoSolicitud.En_espera);
	        
	       
	        	soli= this.solicitudService.save(soli);
	        
	        	this.solicitudService.SolicitudHasMateriasOfrecidas(soli.getId(),idsOfrecimiento);
	        
	        	return this.mapper.writeValueAsString(folio.getCodigo());
	        }
	        
	       else {
	    	   //respues a que ya existe folio
	    	   return this.mapper.writeValueAsString(0);
	        }
	}
	
	@RequestMapping(value = "/getSolicitud", method = RequestMethod.POST)
	public String getInstitutos(@RequestBody String stringJson) throws JsonProcessingException {
		try {
			this.mapper= new ObjectMapper();		
			
			Folio aux=this.mapper.readValue(stringJson,Folio.class);
			
			Folio folio= this.folioController.getOne(aux.getCodigo());
			AlumnoModel alumnoModel=new AlumnoModel();
			CarreraModel carreraModel= new CarreraModel();
			
			List<Solicitud> solicitudes = this.solicitudService.findAll(folio.getId());
			List<SolicitudModel> solicitudesModel = new ArrayList<SolicitudModel>();
			for(Solicitud solicitud: solicitudes) {
				
				SolicitudModel solicitudModel= new SolicitudModel();
				//repensarlo
				Alumno alumno = this.alumnoController.getOne(solicitud.getIdAlumno());
				alumnoModel.setNombre(alumno.getNombre());
				alumnoModel.setApellido(alumno.getApellido());
				alumnoModel.setMail(alumno.getEmail());
				alumnoModel.setDni(alumno.getDni());
				alumnoModel.setTelefono(alumno.getTelefono());
				alumnoModel.setLegajo(alumno.getLegajo());
				Carrera carrera=new Carrera();
				carrera=this.carreraController.getOne(alumno.getIdCarrera());
				carreraModel.setNombre(carrera.getNombre());
				alumnoModel.setCarrera(carreraModel);
				
				solicitudModel.setmateriaUngs(this.solicitudService.findMateria(solicitud.getId()));
				solicitudModel.setSolicitudOfrecimiento(this.solicitudService.findMateriaOfrecimiento(solicitud.getId()));
				solicitudModel.setAlumno(alumnoModel);
				solicitudModel.setEstado(solicitud.getEstado());
				
				solicitudesModel.add(solicitudModel);
				
			}
			
			
			return this.mapper.writeValueAsString(solicitudesModel);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.NOT_FOUND.value(),"No encontrado"));
		
		
	}
	
	
}
