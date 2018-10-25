package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;



public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

	@SuppressWarnings("unchecked")
	Alumno save(Alumno alumno);
	
}
