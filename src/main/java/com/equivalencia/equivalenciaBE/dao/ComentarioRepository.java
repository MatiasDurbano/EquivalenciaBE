package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
	
	
	@SuppressWarnings("unchecked")
	Comentario save(Comentario comentario);

	@Query(value = "select * from comentario where comentario.id = :id", nativeQuery = true)
	Comentario findComentario(@Param("id")long id);
}
