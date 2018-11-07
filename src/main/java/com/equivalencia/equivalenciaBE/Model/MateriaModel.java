package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

public class MateriaModel {
	
	private String nombre;
	private int horas;
	private String instituto;
	private List<Byte> plan;
	private AlumnoModel alumno;
	
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
	public String getInstituto() {
		return instituto;
	}
	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	public List<Byte> getPlan() {
		return plan;
	}
	public void setPlan(List<Byte> plan) {
		this.plan = plan;
	}
	public AlumnoModel getAlumno() {
		return alumno;
	}
	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}
	

}
