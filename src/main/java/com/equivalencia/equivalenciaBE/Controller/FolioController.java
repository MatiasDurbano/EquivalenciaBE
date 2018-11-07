package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.Service.FolioService;
import com.equivalencia.equivalenciaBE.Utilities.Codificador;

@RestController
public class FolioController {
	
	@Autowired
	protected FolioService folioService;
	
	public Folio crearFolio() {
		
		Folio aux= new Folio();
		aux.setCodigo(Codificador.getAlfanumerico());
		return this.folioService.save(aux);
	}
	
	public Folio guardarFolio(Folio folio) {
		return this.folioService.save(folio);
	}
	
	public Folio getOne(String codigo) {
		return this.folioService.getOne(codigo);
	}
	
}
