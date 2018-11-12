package com.equivalencia.equivalenciaBE.Model;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;

public class AlumnoSolicitud {
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	private String telefono;
	
	private String dni;

	private String legajo;

	private String carrera;
	
	private String analitico;
	
	private String constancia;

	
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

	public String getAnalitico() {
		return analitico;
	}

	public void setAnalitico(String analitico) {
		this.analitico = analitico;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}
	
	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	
	public Alumno getAlumno() {
		Alumno alumno=new Alumno();
		alumno.setApellido(apellido);
		alumno.setDni(dni);
		alumno.setEmail(email);
		alumno.setLegajo(legajo);
		alumno.setNombre(nombre);
		alumno.setTelefono(telefono);
		alumno.setIdCarrera(0);
		alumno.setIdCertificados(0);
		
		return alumno;
	}
	
	
	public Certificado getCertificado() {
		Certificado ret = new Certificado();
		ret.setConstancia(this.constancia);
		ret.setAnalitico(this.analitico);
		return ret;
		}
	

}
