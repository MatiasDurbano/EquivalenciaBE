package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;

public interface DocenteHasMateriasService {

	List<DocenteHasMaterias> encontrarMateriasDeDocente(long docente);

}
