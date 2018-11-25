package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;
import com.equivalencia.equivalenciaBE.dao.DocenteHasMateriasRepository;

@Service
public class DocenteHasMateriasImpl implements DocenteHasMateriasService {

	@Autowired
	protected DocenteHasMateriasRepository docenteHasMaterias;
	
	@Override
	public List<DocenteHasMaterias> encontrarMateriasDeDocente(long docente) {
		return this.docenteHasMaterias.MateriasDeDocente(docente);
	}

	@Override
	public List<DocenteHasMaterias> encontrarDocentesDeMateria(long materia) {
		return this.docenteHasMaterias.DocenteDeMateria(materia);
	}

	@Override
	public void borrarMateriasDocente(long id) {
		this.docenteHasMaterias.borrarMateriasDocente(id);
		
	}
	
	@Override
	public void save(DocenteHasMaterias docenteHas) {
		this.docenteHasMaterias.save(docenteHas);
		
	}

}
