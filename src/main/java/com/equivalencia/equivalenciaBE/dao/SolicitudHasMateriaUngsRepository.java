package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;

public interface SolicitudHasMateriaUngsRepository extends JpaRepository<SolicitudHasMateriasUngs,Long> {

	@Query(value = "Select * FROM materias_has_solicitudes where materias_has_solicitudes.idmateria=:materia", nativeQuery = true)
	List<SolicitudHasMateriasUngs> findAll(@Param("materia")long id);
	
	@SuppressWarnings("unchecked")
	SolicitudHasMateriasUngs save(SolicitudHasMateriasUngs solicitudHas);

	@Query(value = "Select * FROM materias_has_solicitudes m where m.idsolicitud=:id", nativeQuery = true)
	List<SolicitudHasMateriasUngs> obtenerPorSolicitud(@Param("id")long id);



}
