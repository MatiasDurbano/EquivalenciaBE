package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;

public interface SolicitudHasMateriaRepository extends JpaRepository<SolicitudHasMateria,Long>{
	
	@SuppressWarnings("unchecked")
	SolicitudHasMateria save(SolicitudHasMateria solicitudHasMateria);

}
