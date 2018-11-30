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
		Comentario comentario= new Comentario();
		comentario.setComentario("");
		return this.comentarioService.save(comentario);
	}
	
	public Comentario findComentario(long id) {
		return this.comentarioService.findComentario(id);
	}

	public void actualizarComentario(Comentario comentario) {
		this.comentarioService.actualizarComentario(comentario);
		
	}
}
