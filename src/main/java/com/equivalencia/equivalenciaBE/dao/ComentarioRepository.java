package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
	
	
	@SuppressWarnings("unchecked")
	Comentario save(Comentario comentario);

}
