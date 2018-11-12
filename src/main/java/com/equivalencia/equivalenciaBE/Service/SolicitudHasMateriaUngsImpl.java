package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;
import com.equivalencia.equivalenciaBE.dao.SolicitudHasMateriaUngsRepository;

@Service
public class SolicitudHasMateriaUngsImpl implements SolicitudHasMateriaUngsService {

	@Autowired
	protected SolicitudHasMateriaUngsRepository solicitudHasMateriaRepository;
	
	
	@Override
	public List<SolicitudHasMateriasUngs> findAll(long id) {
		return this.solicitudHasMateriaRepository.findAll(id);
	}


	@Override
	public void save(SolicitudHasMateriasUngs solicitudHas) {
		this.solicitudHasMateriaRepository.save(solicitudHas);
		
	}

}
