package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.dao.MateriasHasCarreraRepository;


@Service
public class MateriasHasCarreraServiceImpl implements MateriasHasCarreraService {

	@Autowired
	protected MateriasHasCarreraRepository materiasHas;
	
	@Override
	public MateriasHasCarrera save(MateriasHasCarrera materiasHas) {
		return  this.materiasHas.save(materiasHas);
	}
	
	@Override
	public List<MateriasHasCarrera> findMateriHasCarreraPorIdCarrera(long id) {
		return this.materiasHas.findMateriHasCarrera(id);
	}

}
