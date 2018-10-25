package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "instituto")
@Access(AccessType.FIELD)
public class Instituto extends ParentEntity {

	private static final long serialVersionUID = 172092432402824749L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
