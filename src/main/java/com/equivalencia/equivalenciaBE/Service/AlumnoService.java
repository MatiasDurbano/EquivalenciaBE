package com.equivalencia.equivalenciaBE.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;

public interface AlumnoService {
	
	Alumno save(Alumno alumno);

	Alumno getOne(long id);

	boolean existe(Alumno alumno);
	

}
