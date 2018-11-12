package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;
import com.equivalencia.equivalenciaBE.Service.PlanOfrecidaService;

@Controller
public class PlanController {

	
	@Autowired
	protected PlanOfrecidaService planOfrecidaService;


	public PlanMateriaOfrecida saveOfrecida(PlanMateriaOfrecida plan) {
		return this.planOfrecidaService.save(plan);
	}


	public String getOneOfrecida(long idPlan) {
		PlanMateriaOfrecida plan = this.planOfrecidaService.getOne(idPlan);
		return plan.getDocumentacion();
	}


	
	
}
