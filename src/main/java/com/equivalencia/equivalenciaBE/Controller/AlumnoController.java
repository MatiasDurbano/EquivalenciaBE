package com.equivalencia.equivalenciaBE.Controller;

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

}
