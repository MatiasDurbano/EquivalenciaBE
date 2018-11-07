package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.equivalencia.equivalenciaBE.Utilities.EstadoSolicitud;

@Entity
@Table(name = "solicitudes")
@Access(AccessType.FIELD)
public class Solicitud extends ParentEntity {

	private static final long serialVersionUID = 5350261864065340928L;

	@Column(name = "idalumno", nullable = false)
	private long idAlumno;
	
	@Column(name = "idfolio", nullable = false)
	private long idFolio;
	
	@Column(name = "iddocente", nullable = true)
	private long idDocente;
	
	@Column(name = "estado", length = 45)		
	private String estado;
	
	@Column(name = "idcomentario")		
	private long comentario;
	
	

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
		return estado.toString();
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado.toString();
	}

	public long getComentario() {
		return comentario;
	}

	public void setComentario(long l) {
		this.comentario = l;
	}
}
