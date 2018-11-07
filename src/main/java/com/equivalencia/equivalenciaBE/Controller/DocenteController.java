package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Service.DocenteService;
import com.equivalencia.equivalenciaBE.Utilities.DocenteFirm;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DocenteController {
	
	
	@Autowired
	protected DocenteService docenteService;
	
	protected ObjectMapper mapper;
	
	
	public Docente findOne(Long id) {
		return this.docenteService.findOne(id);
	}
	
	public Docente save(Docente docente) {
		return this.docenteService.save(docente);
	}

	public Docente buildDocente(DocenteFirm docenteFirm) {
		Docente ret = docenteFirm.buildDocente();
		return ret;
	}

	public Docente guardar(Docente docente, long id) {
		docente.setUsuarioId(id);
		return this.docenteService.save(docente);
	}

	public boolean existe(Docente docente) {
		return this.docenteService.existe(docente);
	}
	
	
	
}
