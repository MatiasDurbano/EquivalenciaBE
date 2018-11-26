package com.equivalencia.equivalenciaBE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Service.AlumnoService;


@RestController
public class AlumnoController {
	
	@Autowired
	protected AlumnoService alumnoService;
	
	
	public Alumno saveAlumno(Alumno alumno) {
		return this.alumnoService.save(alumno);
	}


	public Alumno getOne(long id) {
		return this.alumnoService.getOne(id);
	}


	public boolean existe(Alumno alumno) {
		return this.alumnoService.existe(alumno);
	}


	public Alumno buscarPorEmail(String email) {
		return this.alumnoService.buscarPorEmail(email);
	}


	public List<Alumno> traerPorInstituto(long id) {
		return this.alumnoService.traerPorInstituto(id);
	}

}
