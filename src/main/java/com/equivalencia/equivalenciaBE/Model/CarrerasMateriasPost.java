package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CarrerasMateriasPost {
	
	
	@JsonProperty( "instituto" )
	private  Instituto instituto;
	
	@JsonProperty( "ListCarrera" )
	@JsonDeserialize(as=ArrayList.class, contentAs=CarreraMaterias.class)
	private List<CarreraMaterias> carreraMaterias;
	
	public Instituto getInstituto() {
		return instituto;
	}
	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}
	public List<CarreraMaterias> getCarreraMaterias() {
		return carreraMaterias;
	}
	public void setCarreraMaterias(List<CarreraMaterias> carreraMaterias) {
		this.carreraMaterias = carreraMaterias;
	}

}
