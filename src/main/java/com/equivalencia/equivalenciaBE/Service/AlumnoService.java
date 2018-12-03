package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;

public interface AlumnoService {
	
	Alumno save(Alumno alumno);

	Alumno getOne(long id);

	boolean existe(Alumno alumno);

	Alumno buscarPorEmail(String email);

	List<Alumno> traerPorInstituto(long id);

	List<Alumno> getAll();
	

}
