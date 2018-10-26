package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.dao.SolicitudHasMateriaRepository;
import com.equivalencia.equivalenciaBE.dao.SolicitudOfrecimientoRepository;
import com.equivalencia.equivalenciaBE.dao.SolicitudRepository;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	@Autowired
	protected SolicitudRepository solicitudRepository;
	
	@Autowired
	protected SolicitudOfrecimientoRepository solicitudOfrecimientoRepository;
	
	@Autowired
	protected SolicitudHasMateriaRepository solicitudesHasMateriasRepository;

	@Override
	public Solicitud save(Solicitud solicitud) {
		return this.solicitudRepository.save(solicitud);
	}

	@Override
	public SolicitudOfrecimiento save(SolicitudOfrecimiento solicitud) {
		return this.solicitudOfrecimientoRepository.save(solicitud);
	}

	@Override
	public void SolicitudHasMateriasOfrecidas(long id, List<Long> idsOfrecimiento) {
		for(Long idOfrecimiento: idsOfrecimiento) {
			
			this.solicitudesHasMateriasRepository.save(new SolicitudHasMateria(id,idOfrecimiento));
			
		}
	}
	
	
	
	
}
