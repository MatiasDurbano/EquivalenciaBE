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
	public Materia findAll(long primeraClave) {
		return this.materiaRepository.encontrarPorClave(primeraClave);
	}

	@Override
	public Materia save(Materia materia) {
		return this.materiaRepository.save(materia);
	}


	@Override
	public Materia getOne(String nombre) {
		
		List<Materia>ret =this.materiaRepository.getOne(nombre);
		return ret.get(0);
	}


	@Override
	public List<Materia> findAllMateriasPorNombre(String nombre,String carrera) {
		return this.materiaRepository.findAllPorNombre(nombre,carrera);
	}


	@Override
	public Materia findOne(long segundaClave) {
		return this.materiaRepository.encontrarPorClave(segundaClave);
	}


	@Override
	public List<Materia> buscarMateriaPorInstituto(long id) {
		return this.materiaRepository.buscarMateriaPorInstituto(id);
	}


	@Override
	public Materia buscarPorSolicitud(long id) {
		return this.materiaRepository.buscarPorSolicitud(id);
	}
	

}
