package com.equivalencia.equivalenciaBE.Model;

import javax.persistence.Column;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;

public class Administrador {
	private String nombre;
	
	private String apellido;
	
	private int tipo;
	
	private String instituto;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apelido) {
		this.apellido = apelido;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
}
