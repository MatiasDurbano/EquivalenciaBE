package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;


public interface DocenteRepository extends JpaRepository<Docente, Long> {
	
	@SuppressWarnings("unchecked")
	Docente save(Docente docente);

	
	@Query(value = "Select * FROM docente where docente.usuario_id= :id ", nativeQuery = true)
	Docente  getOne(@Param("id")Long id);
	
}
