package com.equivalencia.equivalenciaBE.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;
import com.equivalencia.equivalenciaBE.dao.MateriaRepository;


@Service
public class MateriaServiceImpl implements MateriaService {
	
	@Autowired
	protected MateriaRepository materiaRepository;

	@Override
	public List<Materia> findAll(String carrera) {
		return this.materiaRepository.findAll(carrera);
	}


	@Override
	public List<Materia> findAll(long primeraClave) {
		return this.materiaRepository.findAll(primeraClave);
	}

	@Override
	public Materia save(Materia materia) {
		return this.materiaRepository.save(materia);
	}


	@Override
	public Materia getOne(String nombre) {
		return this.materiaRepository.getOne(nombre);
	}


}
