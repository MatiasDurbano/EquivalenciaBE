package com.equivalencia.equivalenciaBE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.dao.CarreraRepository;

@Service
public class CarreraServiceImpl implements CarreraService {

	@Autowired
	protected CarreraRepository carreraRepository;
	
	@Override
	public List<Carrera> findAll() {
		return this.carreraRepository.findAll();
	}

	@Override
	public Carrera getOne(long idCarrera) {
		return this.carreraRepository.getOne(idCarrera);
	}

	@Override
	public List<Carrera> findAllporInstituto(String nombre) {
		return this.carreraRepository.findAllporInstituto(nombre);
	}

	@Override
	public Carrera getOne(String carrera) {
		return this.carreraRepository.getOne(carrera);
	}

	@Override
	public List<Carrera> buscarPorInstituto(long l) {
		return this.carreraRepository.buscarPorInstituto(l);
		
		
	}

	@Override
	public void actualizarDisponibilidad(String carrera, long disponible) {
		 this.carreraRepository.actualizarDisponiblidad(carrera, disponible);
	}

	@Override
	public Carrera save(Carrera carr) {
		return this.carreraRepository.save(carr);
	}

	@Override
	public void borrarCarrera(String nombre) {
		this.carreraRepository.borrarCarrera(nombre);
	}

	
}
