package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;

public interface PlanOfrecidaRepository extends JpaRepository<PlanMateriaOfrecida,Long>{ 
	
	@SuppressWarnings("unchecked")
	PlanMateriaOfrecida save(PlanMateriaOfrecida plan);
	
	
	@Query(value = "Select * FROM plan_materiaofrecida where plan_materiaofrecida.id = :id ", nativeQuery = true)
	PlanMateriaOfrecida getOne(@Param("id")long id);
	
	
}
