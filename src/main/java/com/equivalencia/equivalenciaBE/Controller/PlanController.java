package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanEstudio;
import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;
import com.equivalencia.equivalenciaBE.Service.PlanOfrecidaService;
import com.equivalencia.equivalenciaBE.Service.PlanEstudioService;

@Controller
public class PlanController {

	
	@Autowired
	protected PlanOfrecidaService planOfrecidaService;
	
	@Autowired
	protected PlanEstudioService PlanEstudioService;


	public PlanMateriaOfrecida saveOfrecida(PlanMateriaOfrecida plan) {
		return this.planOfrecidaService.save(plan);
	}


	public String getOneOfrecida(long idPlan) {
		PlanMateriaOfrecida plan = this.planOfrecidaService.getOne(idPlan);
		return plan.getDocumentacion();
	}


	public PlanEstudio savePlanUngs(PlanEstudio plan) {
		return this.PlanEstudioService.save(plan);
	}


	
	
}
