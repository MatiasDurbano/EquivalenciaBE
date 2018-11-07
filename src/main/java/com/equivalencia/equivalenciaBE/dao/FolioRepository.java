package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;


public interface FolioRepository extends JpaRepository<Folio, Long> {

	@SuppressWarnings("unchecked")
	Folio save(Folio folio);
	
	@Query(value = "Select * FROM folios where folios.codigo = :codigo ", nativeQuery = true)
	Folio getOne(@Param("codigo")String codigo);

}


