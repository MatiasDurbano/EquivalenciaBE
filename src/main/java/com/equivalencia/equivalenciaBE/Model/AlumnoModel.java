package com.equivalencia.equivalenciaBE.Model;

import javax.persistence.Column;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;
import com.equivalencia.equivalenciaBE.Model.TablasDb.ParentEntity;

public class AlumnoModel extends ParentEntity {

	private static final long serialVersionUID = 6203284604343164093L;
	
	private String nombre;
	
	private String apellido;
	
	private String mail;
	
	private String telefono;

	private String dni;

	private String legajo;

	private Carrera carrera;
	
	private Certificado certificado;

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

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Certificado getCertificado() {
		return certificado;
	}

	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}

}