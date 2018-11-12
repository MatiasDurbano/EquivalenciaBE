package com.equivalencia.equivalenciaBE.Model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AsignaturaEquivalente {
	
	@JsonProperty( "nombre" )
	private String nombre;
	
	@JsonProperty( "anoAprobacion" )
	private int anioAprobacion;
	
	@JsonProperty( "cargaHoraria" )
	private int cargaHoraria;
	
	@JsonProperty( "documentacion" )
	private String documentacion;
	
	@JsonProperty( "institutoOrigen" )
	private String universidad;

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

	public String getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	
}
