package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
	
	
	@SuppressWarnings("unchecked")
	Comentario save(Comentario comentario);

	@Query(value = "select * from comentario where comentario.id = :id", nativeQuery = true)
	Comentario findComentario(@Param("id")long id);
	
	@Modifying
	@Transactional
	@Query(value = "update comentario c set c.comentario=:comentario where c.id = :id", nativeQuery = true)	
	void actualizarComentario(@Param("id")long id,@Param("comentario")String comentario);
}
