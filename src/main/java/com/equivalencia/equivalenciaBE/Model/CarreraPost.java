package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

public class CarreraPost {
	
	private String instituto;
	
	private List<CarreraAngular> carreras;

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public List<CarreraAngular> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraAngular> carreras) {
		this.carreras = carreras;
	}

}
