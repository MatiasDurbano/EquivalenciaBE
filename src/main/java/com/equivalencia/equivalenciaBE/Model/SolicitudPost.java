package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudPost {
	
	 @JsonProperty( "alumno" )
	private AlumnoSolicitud alumno;
	
	 @JsonProperty( "asignaturasUNGS" )
	@JsonDeserialize(as=ArrayList.class, contentAs=SolicitudModel.class)
	private List<SolicitudModel> solicitudesModel;

	 
	public Alumno getAlumno() {
		return alumno.getAlumno();
	}

	public void setAlumno(AlumnoSolicitud alumno) {
		this.alumno = alumno;
	}

	public List<SolicitudModel> getSolicitudesModel() {
		return solicitudesModel;
	}

	public void setsolicitudesModel(List<SolicitudModel> solicitudesModel) {
		this.solicitudesModel = solicitudesModel;
	}
	
	public Certificado getCertificado() {
		return alumno.getCertificado();
	}
	
	public String getCarrera() {
		return this.alumno.getCarrera();
	}

	
}
