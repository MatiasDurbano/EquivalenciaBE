package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Service.ComentarioService;

@RestController
public class ComentarioController {
	
	@Autowired
	protected ComentarioService comentarioService;
	
	
	public Comentario crearComentario() {
		return this.comentarioService.save(new Comentario());
	}
	
	public Comentario findComentario(long id) {
		return this.comentarioService.findComentario(id);
	}
}
