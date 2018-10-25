package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.ParentEntity;

public class CarreraModel{
	
	private String nombre;
	
	private List<Materia> Materias;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Materia> getMaterias() {
		return Materias;
	}

	public void setMaterias(List<Materia> materias) {
		Materias = materias;
	}

}
