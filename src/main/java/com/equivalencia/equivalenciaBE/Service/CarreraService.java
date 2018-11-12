package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;

public interface CarreraService {
	
	List<Carrera> findAll();

	Carrera getOne(long l);

	List<Carrera> findAllporInstituto(String nombre);

	Carrera getOne(String carrera);

}
