package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CarreraMaterias {
	
	@JsonProperty( "carrera" )
	private String carrera;
	
	@JsonProperty( "materias" )
	@JsonDeserialize(as=ArrayList.class, contentAs=MateriaModelAdmin.class)
	private List<MateriaModelAdmin> materias;
	
	public CarreraMaterias() {
		 materias= new ArrayList<MateriaModelAdmin>();
	}
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public List<MateriaModelAdmin> getMaterias() {
		return materias;
	}
	public void setMaterias(List<MateriaModelAdmin> materiaModelAdmin) {
		this.materias = materiaModelAdmin;
	}
	

}
