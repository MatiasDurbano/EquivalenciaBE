package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;

public interface InstitutoRepository extends JpaRepository<Instituto,Long>{
	
	@SuppressWarnings("unchecked")
	Instituto save(Instituto instituto);
	
	List<Instituto> findAll();

	@Query(value = "Select * FROM instituto where instituto.nombre = :nombre ", nativeQuery = true)
	Instituto getOne(@Param("nombre")String instituto);


	@Query(value = "Select * FROM instituto where instituto.id = :id ", nativeQuery = true)
	Instituto getOne(@Param("id")long id);

	@Modifying
	@Transactional
	@Query(value = "update instituto i set i.disponible=1 where i.nombre = :nombre ", nativeQuery = true)
	void borrarInstituto(@Param("nombre")String nombre);

}
