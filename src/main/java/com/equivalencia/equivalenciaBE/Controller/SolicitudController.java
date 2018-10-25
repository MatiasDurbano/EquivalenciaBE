package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Utilities.SolicitudPost;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class SolicitudController {

	@Autowired
	AlumnoController alumnoController;
	
	protected ObjectMapper mapper;
	
	
	@RequestMapping(value = "/solicitud", method = RequestMethod.POST)
	public Object getUsuario(@RequestBody String solicitudJson) throws JsonParseException, JsonMappingException, IOException{
			this.mapper= new ObjectMapper();
			
			this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	        SolicitudPost solicitud=mapper.readValue(solicitudJson, SolicitudPost.class);
	        return solicitud;
		
	}
	
}
