package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")
@Access(AccessType.FIELD)
public class Alumno extends ParentEntity  {

	private static final long serialVersionUID = 2440205330565601592L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 45)
	private String apellido;
	
	@Column(name = "email", nullable = false, length = 80)		
	private String email;
	
	@Column(name = "telefono",nullable = false, length = 45)		
	private String telefono;

	@Column(name = "dni",nullable = false, length = 45)		
	private String dni;

	@Column(name = "legajo",nullable = false, length = 45)		
	private String legajo;

	@Column(name = "idcarrera")		
	private int idCarrera;
	
	@Column(name = "idcertificados")
	private int idCertificados;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public int getIdCertificados() {
		return idCertificados;
	}

	public void setIdCertificados(int idCertificados) {
		this.idCertificados = idCertificados;
	}

	
	
}
