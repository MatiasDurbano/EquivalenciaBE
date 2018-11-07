package com.equivalencia.equivalenciaBE.Model;

import java.util.List;


//Clase que sirve para cargar los institutos en angular, NO se usa para guardar nada
public class InstitutosUngs {
	
	private String nombre;
	
	private List<String> carreras;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<String> carreras) {
		this.carreras = carreras;
	}
	
	

}
