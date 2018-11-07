package com.equivalencia.equivalenciaBE.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;

import com.equivalencia.equivalenciaBE.Service.InstitutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class InstitutoController {
	
	@Autowired
	protected InstitutoService insitutoService;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/institutos", method = RequestMethod.GET)
	public String getInstitutos() throws JsonProcessingException {
		this.mapper= new ObjectMapper();
		
		List<Instituto> ret = this.insitutoService.findAll();
		
		return this.mapper.writeValueAsString(ret);
		
	}
	
	
	
	
}
