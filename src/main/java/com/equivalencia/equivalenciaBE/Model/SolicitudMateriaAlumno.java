package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;


public class SolicitudMateriaAlumno {

	private List<AsignaturasUNGS> asignaturasUngs;
	
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

	public List<AsignaturasUNGS> getAsignaturasUNGS() {
		return asignaturasUngs;
	}

	public void setAsignaturasUNGS(List<AsignaturasUNGS> asignaturaUngs) {
		this.asignaturasUngs = asignaturaUngs;
	}

	
}
