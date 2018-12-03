package com.equivalencia.equivalenciaBE.Model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DocentePost {
	
	@JsonProperty( "nombre" )
	private String nombre;
	@JsonProperty( "apellido" )
	private String apellido;
	
	@JsonProperty( "email" )
	private String email;
	
	@JsonProperty( "asignaturas" )
	private List<String> asignaturas;

	@JsonProperty( "disponible" )
	private long disponible;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getasignaturas() {
		return asignaturas;
	}

	public void setasignaturas(List<String> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDisponible() {
		return disponible;
	}

	public void setDisponible(long disponible) {
		this.disponible = disponible;
	}


}
