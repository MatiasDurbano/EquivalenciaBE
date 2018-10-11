package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.Usuario;
import com.equivalencia.equivalenciaBE.Service.UsuarioService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UsuarioController {

	@Autowired
	protected UsuarioService usuarioService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String usuarioJson) throws JsonParseException, JsonMappingException, IOException {
		
		this.mapper= new ObjectMapper();
		
		Usuario usuario= this.mapper.readValue(usuarioJson,Usuario.class);
		
		if (!this.validate(usuario)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"no");
		}
		this.usuarioService.save(usuario);
		return new RestResponse(HttpStatus.OK.value(),"ok");
		
	}

	
	@SuppressWarnings("unused")
	private boolean validate (Usuario usuario) {
		
		boolean isValid = true;
		if(usuario.getNombre() == null)
			isValid = false;
		if(usuario.getContraseña() == null)
			isValid = false;
		
		return isValid;
	}
	
	
}
