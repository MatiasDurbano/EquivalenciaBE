package com.equivalencia.equivalenciaBE.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarreraAngular {

	@JsonProperty( "id" )
	private long id;
	
	@JsonProperty( "nombre" )
	private String nombre;
	
	@JsonProperty( "disponible" )
	private long disponible;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getDisponible() {
		return disponible;
	}

	public void setDisponible(long disponible) {
		this.disponible = disponible;
	}
	
}
