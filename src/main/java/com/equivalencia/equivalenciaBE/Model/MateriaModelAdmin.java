package com.equivalencia.equivalenciaBE.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MateriaModelAdmin {
	
	@JsonProperty( "nombre" )
	private String nombreMateria;
	
	@JsonProperty( "horas" )
	private int horas;
	
	private String plan;

	public String getNombre() {
		return nombreMateria;
	}

	public void setNombre(String nombre) {
		this.nombreMateria = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

}
