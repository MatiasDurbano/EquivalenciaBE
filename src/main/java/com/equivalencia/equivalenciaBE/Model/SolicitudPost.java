package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudPost {
	
	 @JsonProperty( "alumno" )
	private Alumno alumno;
	
	 @JsonProperty( "asignaturasUNGS" )
	@JsonDeserialize(as=ArrayList.class, contentAs=SolicitudModel.class)
	private List<SolicitudModel> solicitudesModel;

	 
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<SolicitudModel> getSolicitudesModel() {
		return solicitudesModel;
	}

	public void setsolicitudesModel(List<SolicitudModel> solicitudesModel) {
		this.solicitudesModel = solicitudesModel;
	}

	
}
