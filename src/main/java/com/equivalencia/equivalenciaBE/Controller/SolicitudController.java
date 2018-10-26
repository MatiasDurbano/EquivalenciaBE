package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.SolicitudModel;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Service.SolicitudService;
import com.equivalencia.equivalenciaBE.Utilities.Codificador;
import com.equivalencia.equivalenciaBE.Utilities.SolicitudPost;
import com.fasterxml.jackson.core.JsonParseException;
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
	
	protected ObjectMapper mapper;
	
	
	@RequestMapping(value = "/solicitud", method = RequestMethod.POST)
	public Object getUsuario(@RequestBody String solicitudJson) throws JsonParseException, JsonMappingException, IOException{
			this.mapper= new ObjectMapper();
			
			this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	        SolicitudPost solicitud=mapper.readValue(solicitudJson, SolicitudPost.class);
	        
	        Folio folio=this.folioController.crearFolio();
	        folio=this.folioController.guardarFolio(folio);
	        
	        Alumno alumno = solicitud.getAlumno();
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
	        
	        //aca deberia buscar al docente que dicte esa materia
	        soli.setIdDocente(1);
	        soli= this.solicitudService.save(soli);
	        
	        this.solicitudService.SolicitudHasMateriasOfrecidas(soli.getId(),idsOfrecimiento);
	        
	        
	        return folio.getCodigo();
		
	}
}
