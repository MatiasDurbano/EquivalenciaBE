package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudModel {
	
	@JsonProperty( "materiaUngs" )
	private String materiaUngs;
	
	@JsonProperty( "equivalencias" )
	@JsonDeserialize(as=ArrayList.class, contentAs=AsignaturaEquivalente.class)
	private List<AsignaturaEquivalente> solicitudOfrecimiento;
	
	private AlumnoSolicitud alumno;
	

	@JsonProperty( "estado" )
	private String estado;
	
	@JsonProperty( "comentario" )
	private String comentario;
	
	@JsonProperty( "disponible" )
	private long disponible;
	
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public AlumnoSolicitud getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoSolicitud alumno) {
		this.alumno = alumno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getDisponible() {
		return disponible;
	}

	public void setDisponible(long disponible) {
		this.disponible = disponible;
	}	

}
