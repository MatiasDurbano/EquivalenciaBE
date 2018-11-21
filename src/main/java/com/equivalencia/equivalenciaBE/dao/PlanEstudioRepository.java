package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanEstudio;

public interface PlanEstudioRepository extends JpaRepository<PlanEstudio,Long> {

	
	@SuppressWarnings("unchecked")
	PlanEstudio save(PlanEstudio plan);
	
}
