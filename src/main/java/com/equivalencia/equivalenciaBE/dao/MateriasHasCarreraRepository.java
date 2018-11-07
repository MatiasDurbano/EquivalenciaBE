package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;

public interface MateriasHasCarreraRepository  extends JpaRepository<MateriasHasCarrera,Long> {
	
	@SuppressWarnings("unchecked")
	MateriasHasCarrera save(MateriasHasCarrera materiasHas);

	@Query(value = "Select * FROM materias_has_carrera where materias_has_carrera.idcarrera=:id", nativeQuery = true)
	List<MateriasHasCarrera> findMateriHasCarrera(long id);
}
