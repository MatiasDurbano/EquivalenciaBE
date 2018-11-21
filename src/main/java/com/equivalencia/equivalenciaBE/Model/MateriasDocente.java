package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//ESTA CLASE SOLO SIRVE PARA OBTENER UN ARREGLO DE MATERIAS QUE DICTA EL DOCENTE
public class MateriasDocente {

	@JsonProperty( "listaMateria" )
	@JsonDeserialize(as=ArrayList.class, contentAs=String.class)
	private List<String> materias;

	public List<String> getMaterias() {
		return materias;
	}

	public void setMaterias(List<String> materias) {
		this.materias = materias;
	}
	
	
	
}
