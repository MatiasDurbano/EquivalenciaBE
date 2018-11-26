package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.dao.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	protected ComentarioRepository comentarioRepository;
	
	@Override
	public Comentario save(Comentario comentario) {
		return this.comentarioRepository.save(comentario);
	}

	@Override
	public Comentario findComentario(long id) {
		return this.comentarioRepository.findComentario(id);
	}

	@Override
	public void actualizarComentario(Comentario comentario) {
		this.comentarioRepository.actualizarComentario(comentario.getId(),comentario.getComentario());// TODO Auto-generated method stub
		
	}

	
}
