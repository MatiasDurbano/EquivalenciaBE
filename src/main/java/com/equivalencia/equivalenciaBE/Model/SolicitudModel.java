package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SolicitudModel {
	
	@JsonProperty( "nombre" )
	private String materiaUngs;
	
	@JsonProperty( "AsignaturaEquivalente" )
	@JsonDeserialize(as=ArrayList.class, contentAs=SolicitudOfrecimiento.class)
	private List<SolicitudOfrecimiento> solicitudOfrecimiento;
	
	private Comentario comentario;
	
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

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}	

}
