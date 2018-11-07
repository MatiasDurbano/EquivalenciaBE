package com.equivalencia.equivalenciaBE.Service;

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
	public Carrera getOne(int idCarrera) {
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

	
}
