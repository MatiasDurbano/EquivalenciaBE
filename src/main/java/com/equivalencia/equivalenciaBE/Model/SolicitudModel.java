package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudModel {
	
	@JsonProperty( "list" )
	@JsonDeserialize(as=ArrayList.class, contentAs=SolicitudOfrecimiento.class)
	private List<SolicitudOfrecimiento> solicitudOfrecimiento;
	
	@JsonProperty( "materiaUngs" )
	private String materiaUngs;
	
	public String getmateriaUngs() {
		return materiaUngs;
	}

	public void setmateriaUngs(String materiaUngs) {
		this.materiaUngs = materiaUngs;
	}

	public List<SolicitudOfrecimiento> getSolicitudOfrecimiento() {
		return solicitudOfrecimiento;
	}

	public void setSolicitudOfrecimiento(List<SolicitudOfrecimiento> solicitudOfrecimiento) {
		this.solicitudOfrecimiento = solicitudOfrecimiento;
	}	

}
