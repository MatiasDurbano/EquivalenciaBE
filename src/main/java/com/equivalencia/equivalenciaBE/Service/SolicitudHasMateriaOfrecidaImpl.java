package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.dao.SolicitudHasMateriaRepository;


@Service
public class SolicitudHasMateriaOfrecidaImpl implements SolicitudHasMateriaOfrecidaService {

	@Autowired
	protected SolicitudHasMateriaRepository solicicitudHasMateria;
	
	@Override
	public List<SolicitudHasMateria> findAll(long id) {
		return this.solicicitudHasMateria.findAllSolicitudHas(id);
	}

}
