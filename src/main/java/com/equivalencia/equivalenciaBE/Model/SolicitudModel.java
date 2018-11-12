package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudModel {
	
	@JsonProperty( "nombre" )
	private String materiaUngs;
	
	@JsonProperty( "equivalencias" )
	@JsonDeserialize(as=ArrayList.class, contentAs=AsignaturaEquivalente.class)
	private List<AsignaturaEquivalente> solicitudOfrecimiento;
	
	private AlumnoModel alumno;
	
	private String estado;
	
	private Comentario comentario;
	
	public String getmateriaUngs() {
		return materiaUngs;
	}

	public void setmateriaUngs(String materiaUngs) {
		this.materiaUngs = materiaUngs;
	}

	public List<AsignaturaEquivalente> getAsignaturaEquivalente() {
		return solicitudOfrecimiento;
	}

	public void setAsignaturaEquivalente(List<AsignaturaEquivalente> solicitudOfrecimiento) {
		this.solicitudOfrecimiento = solicitudOfrecimiento;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
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

}
