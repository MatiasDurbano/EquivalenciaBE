package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.AdminPost;
import com.equivalencia.equivalenciaBE.Model.Administrador;
import com.equivalencia.equivalenciaBE.Model.Email;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Service.AdminService;
import com.equivalencia.equivalenciaBE.Service.InstitutoService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminController {

	@Autowired
	protected AdminService adminService;
	
	@Autowired
	protected InstitutoService institutoService;
	
	protected ObjectMapper mapper;
	
	public Admin findOne(Long id) {
		return this.adminService.findOne(id);
	}
	
	public Admin save(Admin admin) {
		return this.adminService.save(admin);
	}
	
	@RequestMapping(value = "/obtenerAdmin", method = RequestMethod.POST)
	public String getInstitutos(@RequestBody String solicitudJson) throws IOException {
		
		this.mapper= new ObjectMapper();
		
		System.out.println(solicitudJson);
		
		Email email = this.mapper.readValue(solicitudJson, Email.class);
		
		Admin admin= this.adminService.buscarPorEmail(email.getEmail());
		
		AdminPost ret = new AdminPost();
		
		ret.setApellido(admin.getApelido());
		ret.setNombre(admin.getNombre());
		ret.setEmail(email.getEmail());
		ret.setInstituto(this.institutoService.getOne(admin.getIdInstituto()).getNombre());
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
		
	}
	
	
}
