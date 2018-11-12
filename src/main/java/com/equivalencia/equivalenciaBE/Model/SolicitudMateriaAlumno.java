package com.equivalencia.equivalenciaBE.Model;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;


public class SolicitudMateriaAlumno {

	
	private String materiaUngs;
	
	private AsignaturaEquivalente solicitudOfrecimiento;
	
	private AlumnoModel alumno;
	
	private String estado;
	
	private Comentario comentario;

	public String getMateriaUngs() {
		return materiaUngs;
	}

	public void setMateriaUngs(String materiaUngs) {
		this.materiaUngs = materiaUngs;
	}

	public AsignaturaEquivalente getSolicitudOfrecimiento() {
		return solicitudOfrecimiento;
	}

	public void setSolicitudOfrecimiento(AsignaturaEquivalente solicitudOfrecimiento) {
		this.solicitudOfrecimiento = solicitudOfrecimiento;
	}

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

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	
	

	
}
