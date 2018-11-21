package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;

public interface SolicitudOfrecimientoRepository extends JpaRepository<SolicitudOfrecimiento,Long>{

	@SuppressWarnings("unchecked")
	SolicitudOfrecimiento save (SolicitudOfrecimiento solicitud);

	
}
