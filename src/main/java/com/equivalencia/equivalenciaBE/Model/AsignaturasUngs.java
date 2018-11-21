package com.equivalencia.equivalenciaBE.Model;

import java.util.List;

public class AsignaturasUngs {

	private String materiaUngs;
	
	private List<AsignaturaEquivalente> equivalencias;

	public String getMateriaUngs() {
		return materiaUngs;
	}

	public void setMateriaUngs(String materiaUngs) {
		this.materiaUngs = materiaUngs;
	}

	public List<AsignaturaEquivalente> getEquivalencias() {
		return equivalencias;
	}

	public void setEquivalencias(List<AsignaturaEquivalente> equivalencias) {
		this.equivalencias = equivalencias;
	}
	
	
	
}
