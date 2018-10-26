package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;

public interface SolicitudOfrecimientoRepository extends JpaRepository<SolicitudOfrecimiento,Long>{

	@SuppressWarnings("unchecked")
	SolicitudOfrecimiento save (SolicitudOfrecimiento solicitud);
	
}
