package com.equivalencia.equivalenciaBE.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanMateriaOfrecida;

public interface PlanOfrecidaService {
	
	PlanMateriaOfrecida save(PlanMateriaOfrecida plan);

	PlanMateriaOfrecida getOne(long idPlan);
	

}
