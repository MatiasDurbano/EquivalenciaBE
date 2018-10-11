package com.equivalencia.equivalenciaBE.Model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@Access(AccessType.FIELD)
public class Usuario extends ParentEntity  {

	private static final long serialVersionUID = -1700100403942817588L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "contraseña", nullable = false, length = 45)
	private String contraseña;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
}
