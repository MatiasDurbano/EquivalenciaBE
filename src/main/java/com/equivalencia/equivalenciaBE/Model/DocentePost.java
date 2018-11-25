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
	
	@JsonProperty( "materias" )
	private List<String> materiasDocente;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getMateriasDocente() {
		return materiasDocente;
	}

	public void setMateriasDocente(List<String> materiasDocente) {
		this.materiasDocente = materiasDocente;
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


}
