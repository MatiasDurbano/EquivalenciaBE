package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud")
@Access(AccessType.FIELD)
public class Solicitud extends ParentEntity {

	private static final long serialVersionUID = 5350261864065340928L;

	@Column(name = "idAlumno", nullable = false)
	private long idAlumno;
	
	@Column(name = "idFolio", nullable = false)
	private long idFolio;
	
	@Column(name = "idDocente", nullable = true)
	private long idDocente;
	
	@Column(name = "estado", length = 45)		
	private String estado;
	
	@Column(name = "comentario", length = 45)		
	private String comentario;
	
	

	public long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public long getIdFolio() {
		return idFolio;
	}

	public void setIdFolio(long idFolio) {
		this.idFolio = idFolio;
	}

	public long getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(long idDocente) {
		this.idDocente = idDocente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	

	
}
