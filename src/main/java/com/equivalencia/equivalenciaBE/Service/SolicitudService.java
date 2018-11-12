package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;

public interface SolicitudService {

	Solicitud save(Solicitud solicitud);
	
	SolicitudOfrecimiento save(SolicitudOfrecimiento solicitud);

	void SolicitudHasMateriasOfrecidas(long id, List<Long> idsOfrecimiento);

	List<Solicitud> findAll(long id);

	String findMateria(long id);

	List<SolicitudOfrecimiento> findMateriaOfrecimiento(long id);

	Solicitud getOne(long segundaClave);
	
	
}
