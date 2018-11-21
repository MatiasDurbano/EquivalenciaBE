package com.equivalencia.equivalenciaBE.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

//CLASE QUE SOLO SIRVE PARA RECIBIR CODIGO
public class CodigoAlumno {

	@JsonProperty( "codigo" )
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
