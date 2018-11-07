package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;

public interface SolicitudHasMateriaRepository extends JpaRepository<SolicitudHasMateria,Long>{
	
	@SuppressWarnings("unchecked")
	SolicitudHasMateria save(SolicitudHasMateria solicitudHasMateria);
	
	@Query(value ="select * from solicitudes_has_materia_ofrecida where idsolicitud= :id", nativeQuery = true)
	SolicitudHasMateria getOne(@Param("id")long id);

	@Query(value ="select * from solicitudes_has_materia_ofrecida where idsolicitud= :id", nativeQuery = true)
	List<SolicitudHasMateria> findAllSolicitudHas(@Param("id")long id);

}
