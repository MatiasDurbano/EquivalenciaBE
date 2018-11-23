package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;
import com.equivalencia.equivalenciaBE.Service.DocenteHasMateriasService;
import com.equivalencia.equivalenciaBE.Service.DocenteService;
import com.equivalencia.equivalenciaBE.Utilities.DocenteFirm;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DocenteController {
	
	
	@Autowired
	protected DocenteService docenteService;
	
	@Autowired
	protected DocenteHasMateriasService docenteHasMaterias;
	
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

	public Docente encontrarPorEmail(String email) {
		return this.docenteService.encontrarPorMail(email);
	}
	
	public List<DocenteHasMaterias> encontrarMateriasDeDocente(long docente) {
		return this.docenteHasMaterias.encontrarMateriasDeDocente(docente);
	}
	
}
