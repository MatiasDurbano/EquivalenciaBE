package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
@Access(AccessType.FIELD)
public class Alumno extends ParentEntity  {

	private static final long serialVersionUID = 2440205330565601592L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 45)
	private String apellido;
	
	@Column(name = "mail", nullable = false, length = 80)		
	private String mail;
	
	@Column(name = "telefono",nullable = false, length = 45)		
	private String telefono;

	@Column(name = "dni",nullable = false, length = 45)		
	private String dni;

	@Column(name = "legajo",nullable = false, length = 45)		
	private String legajo;

	@Column(name = "carrera",nullable = false, length = 80)		
	private String carrera;
	
	@Column(name = "idCarrera")		
	private int idCarrera;
	
	@Column(name = "idCertificados")
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	
	
}
