package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;


public interface FolioRepository extends JpaRepository<Folio, Long> {

	@SuppressWarnings("unchecked")
	Folio save(Folio folio);

}


