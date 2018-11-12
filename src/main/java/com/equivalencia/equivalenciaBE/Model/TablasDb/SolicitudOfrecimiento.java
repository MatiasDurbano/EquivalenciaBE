package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Materiaofrecida")
@Access(AccessType.FIELD)
public class SolicitudOfrecimiento extends ParentEntity {
	
	private static final long serialVersionUID = -167353177980675614L;
	
	@JsonProperty("institutoOrigen")
	@Column(name = "universidad", nullable = false, length = 45)
	private String universidad;
	
	
	@JsonProperty( "nombre" )
	@Column(name = "materia", nullable = false, length = 45)
	private String nombre;
	
	@JsonProperty( "anoAprobacion" )
	@Column(name = "anioaprobacion")
	private int anioAprobacion;
	
	@JsonProperty( "cargaHoraria" )
	@Column(name = "horas")
	private int cargaHoraria;
	
	
	@Column(name = "idplan")
	private long idPlan;


	public String getUniversidad() {
		return universidad;
	}


	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getAnioAprobacion() {
		return anioAprobacion;
	}


	public void setAnioAprobacion(int anioAprobacion) {
		this.anioAprobacion = anioAprobacion;
	}


	public int getCargaHoraria() {
		return cargaHoraria;
	}


	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}


	public long getIdPlan() {
		return idPlan;
	}


	public void setIdPlan(long l) {
		this.idPlan = l;
	}
	

	
}
