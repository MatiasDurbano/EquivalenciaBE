package com.equivalencia.equivalenciaBE.Service;

import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.dao.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	protected AlumnoRepository alumnoRepository;

	@Override
	public Alumno save(Alumno alumno) {
		return this.alumnoRepository.save(alumno);
	}
	
	
	
}
