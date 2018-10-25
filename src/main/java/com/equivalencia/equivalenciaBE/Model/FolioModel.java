package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

public class FolioModel {
	
	private AlumnoModel alumno;
	
	private List<SolicitudModel> solicitudes;

	public AlumnoModel getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}

	public List<SolicitudModel> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudModel> solicitudes) {
		this.solicitudes = solicitudes;
	}
	

}
