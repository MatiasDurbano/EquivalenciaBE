package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CarrerasMateriasPost {
	
	
	@JsonProperty( "instituto" )
	private  String instituto;
	
	@JsonProperty( "ListCarrera" )
	@JsonDeserialize(as=ArrayList.class, contentAs=CarreraMaterias.class)
	private List<CarreraMaterias> carreraMaterias;
	
	public String getInstituto() {
		return instituto;
	}
	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	public List<CarreraMaterias> getCarreraMaterias() {
		return carreraMaterias;
	}
	public void setCarreraMaterias(List<CarreraMaterias> carreraMaterias) {
		this.carreraMaterias = carreraMaterias;
	}

}
