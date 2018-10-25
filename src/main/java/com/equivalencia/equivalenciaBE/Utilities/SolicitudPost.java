package com.equivalencia.equivalenciaBE.Utilities;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.SolicitudModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudPost {
	
	 @JsonProperty( "alumno" )
	private Alumno alumno;
	
	 @JsonProperty( "solicitudes" )
	@JsonDeserialize(as=ArrayList.class, contentAs=SolicitudModel.class)
	private List<SolicitudModel> solicitudesOfrecimiento;

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<SolicitudModel> getSolicitudesOfrecimiento() {
		return solicitudesOfrecimiento;
	}

	public void setSolicitudesOfrecimiento(List<SolicitudModel> solicitudesOfrecimiento) {
		this.solicitudesOfrecimiento = solicitudesOfrecimiento;
	}

	
}
