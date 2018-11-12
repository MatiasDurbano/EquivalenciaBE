package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;

public interface MateriaRepository extends JpaRepository<Materia,Long> {

	@SuppressWarnings("unchecked")
	Materia save(Materia materia);
	
	@Query(value = "Select * FROM materias, instituto where instituto.nombre = :nombre and carrera.idinstituto = instituto.id ", nativeQuery = true)
	List<Materia> findAll(@Param("nombre")String carrera);

	@Query(value = "Select * FROM materias where materias.id=:id", nativeQuery = true)
	List<Materia> findAll(@Param("id")long primeraClave);

	@Query(value = "Select * FROM materias where materias.nombre=:nombre", nativeQuery = true)
	Materia getOne(@Param("nombre")String nombre);


}
