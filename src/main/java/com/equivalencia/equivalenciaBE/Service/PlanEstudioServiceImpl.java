package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanEstudio;
import com.equivalencia.equivalenciaBE.dao.PlanEstudioRepository;

@Service
public class PlanEstudioServiceImpl implements PlanEstudioService {

	@Autowired
	protected PlanEstudioRepository planEstudio;
	
	@Override
	public PlanEstudio save(PlanEstudio plan) {
		return this.planEstudio.save(plan);
	}

}
