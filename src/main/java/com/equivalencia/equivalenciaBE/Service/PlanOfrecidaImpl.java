package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;
import com.equivalencia.equivalenciaBE.dao.PlanOfrecidaRepository;

@Service
public class PlanOfrecidaImpl  implements PlanOfrecidaService{

	@Autowired
	protected PlanOfrecidaRepository planOfrecida;
	
	@Override
	public PlanMateriaOfrecida save(PlanMateriaOfrecida plan) {
		return this.planOfrecida.save(plan);
	}

	@Override
	public PlanMateriaOfrecida getOne(long idPlan) {
		return this.planOfrecida.getOne(idPlan);
	}
	
	
	
}
