package com.equivalencia.equivalenciaBE.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;

public interface ComentarioService {
	
	Comentario save(Comentario comentario);

	Comentario findComentario(long id);

	void actualizarComentario(Comentario comentario);
}
