package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carrera")
@Access(AccessType.FIELD)
public class Carrera extends ParentEntity {

	private static final long serialVersionUID = -5117779122315568104L;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "iduniversidad")
	private int idUniversidad;
	
	@Column(name = "idinstituto")
	private long idinstituto;
	
	@Column(name = "disponible")
	private long disponible;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdUniversidad() {
		return idUniversidad;
	}

	public void setIdUniversidad(int idUniversidad) {
		this.idUniversidad = idUniversidad;
	}

	public long getIdinstituto() {
		return idinstituto;
	}

	public void setIdinstituto(long l) {
		this.idinstituto = l;
	}

	public long getDisponible() {
		return disponible;
	}

	public void setDisponible(long disponible) {
		this.disponible = disponible;
	}

}
