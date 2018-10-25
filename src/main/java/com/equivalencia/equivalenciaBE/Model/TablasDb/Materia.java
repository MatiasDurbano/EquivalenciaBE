package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Materia")
@Access(AccessType.FIELD)
public class Materia extends ParentEntity {

	private static final long serialVersionUID = -33864243706511055L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "horas")
	private int horas;
	
	private Instituto instituto;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}
	
}
