package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;


public interface MateriaService {
	
	List<Materia> findAll(String carrera);

	List<Materia> findAll(long primeraClave);

	Materia save(Materia materia);
	
	Materia getOne(String nombre);

	List<Materia> findAllMateriasPorNombre(String nombre, String carrera);

	Materia findOne(long segundaClave);



}
