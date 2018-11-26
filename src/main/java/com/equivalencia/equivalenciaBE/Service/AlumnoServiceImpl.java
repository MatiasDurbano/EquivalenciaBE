package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.dao.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	protected AlumnoRepository alumnoRepository;

	@Override
	public Alumno save(Alumno alumno) {
		return this.alumnoRepository.save(alumno);
	}

	@Override
	public Alumno getOne(long id) {
		return this.alumnoRepository.getOne(id);
	}

	@Override
	public boolean existe(Alumno alumno) {
		List<Alumno> lista=this.alumnoRepository.findAll(alumno.getLegajo(),alumno.getEmail());
		if(lista.size()>=1) {
			return true;
		}
		return false;
		
	}

	@Override
	public Alumno buscarPorEmail(String email) {
		return this.alumnoRepository.buscarPorEmail(email);
	}

	@Override
	public List<Alumno> traerPorInstituto(long id) {
		return this.alumnoRepository.traerPorInstituto(id);
	}
	
	
	
}
