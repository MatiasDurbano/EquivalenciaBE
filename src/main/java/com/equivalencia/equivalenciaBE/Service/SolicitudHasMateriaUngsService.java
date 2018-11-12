package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;

public interface SolicitudHasMateriaUngsService {

	List<SolicitudHasMateriasUngs> findAll(long id);

	void save(SolicitudHasMateriasUngs solicitudHas);

}
