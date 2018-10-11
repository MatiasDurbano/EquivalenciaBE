package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.Docente;


public interface DocenteRepository extends JpaRepository<Docente, Long> {
	
	@SuppressWarnings("unchecked")
	Docente save(Docente docente);

}
