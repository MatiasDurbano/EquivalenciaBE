package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;


public class SolicitudMateriaAlumno {

	private List<AsignaturasUngs> asignaturasUngs;
	
	private AlumnoModel alumno;
	
	private String estado;
	

	public AlumnoModel getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<AsignaturasUngs> getAsignaturaUngs() {
		return asignaturasUngs;
	}

	public void setAsignaturaUngs(List<AsignaturasUngs> asignaturaUngs) {
		this.asignaturasUngs = asignaturaUngs;
	}

	
}
