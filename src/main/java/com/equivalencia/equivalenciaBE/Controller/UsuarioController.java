package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Usuario;
import com.equivalencia.equivalenciaBE.Service.AdminService;
import com.equivalencia.equivalenciaBE.Service.DocenteService;
import com.equivalencia.equivalenciaBE.Service.UsuarioService;
import com.equivalencia.equivalenciaBE.Utilities.DocenteFirm;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UsuarioController {

	@Autowired
	protected UsuarioService usuarioService;
	@Autowired
	protected DocenteController docenteController;
	@Autowired
	protected AdminController adminController;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String usuarioJson) throws JsonParseException, JsonMappingException, IOException {
		
		this.mapper= new ObjectMapper();
		
		Usuario usuario= this.mapper.readValue(usuarioJson,Usuario.class);
		
		if (!this.validate(usuario)) {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),"no");
		}
		this.usuarioService.save(usuario);
		return new RestResponse(HttpStatus.OK.value(),"ok");
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getUsuario(@RequestBody String usuarioJson) throws JsonParseException, JsonMappingException, IOException{
		this.mapper= new ObjectMapper();
		String ret="{}";
		Usuario usuario= this.mapper.readValue(usuarioJson, Usuario.class);
		List<Usuario> usuarios= this.usuarioService.findAll();
		
		if(usuarios.contains(usuario)) {
			Usuario user = this.buscarUsuario(usuario, usuarios);
			
			switch(user.getTipo()) {
				case "Docente" :
						Docente docente = this.docenteController.findOne(user.getId());	
						ret = this.mapper.writeValueAsString(docente);
						return ret;
				case "Admin":
						Admin admin = this.adminController.findOne(user.getId());
						ret = this.mapper.writeValueAsString(admin);
						return ret;
					}
		}		
		return ret;
	}
	
	@RequestMapping(value = "/registroDocente", method = RequestMethod.POST)
	public Docente saveDocente(@RequestBody String usuarioJson) throws JsonParseException, JsonMappingException, IOException{
		this.mapper= new ObjectMapper();	
		
		DocenteFirm docenteFirm= this.mapper.readValue(usuarioJson, DocenteFirm.class);
		
		Docente docente=this.docenteController.buildDocente(docenteFirm);;
		
		Usuario user= new Usuario();
		docenteFirm.buildUsuario(user);
		user=this.usuarioService.save(user);
		
		return this.docenteController.guardar(docente,user.getId() );
		
	}
	
	
	private Usuario buscarUsuario(Usuario user, List<Usuario> users) {
		for(Usuario usuarios: users) {
			if(user.equals(usuarios)) {
				return usuarios;
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unused")
	private boolean validate (Usuario usuario) {
		
		boolean isValid = true;
		if(usuario.getUsername() == null)
			isValid = false;
		if(usuario.getPassword() == null)
			isValid = false;
		
		return isValid;
	}
	
	private boolean validate (Docente docente) {
		
		boolean isValid = true;
		if(docente.getNombre().equals("")|| docente.getApellido().equals("")||
				docente.getMail().equals(""))
			isValid = false;
		return isValid;
		
	}
	
	
	
}
