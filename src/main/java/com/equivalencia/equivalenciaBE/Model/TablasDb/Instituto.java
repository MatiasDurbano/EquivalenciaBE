package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "instituto")
@Access(AccessType.FIELD)
public class Instituto extends ParentEntity {

	private static final long serialVersionUID = 172092432402824749L;

	@JsonProperty( "nombre" )
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@JsonProperty( "disponible" )
	private long disponible;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getDisponible() {
		return disponible;
	}

	public void setDisponible(long disponible) {
		this.disponible = disponible;
	}

}
