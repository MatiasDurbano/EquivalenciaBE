package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.dao.MateriasHasCarreraRepository;

public interface MateriasHasCarreraService {
	MateriasHasCarrera save(MateriasHasCarrera materiasHas);
	
	public List<MateriasHasCarrera> findMateriHasCarreraPorIdCarrera(long id);

	void actualizarMateriaDisponible(long id, long id2, long disponible);

}
