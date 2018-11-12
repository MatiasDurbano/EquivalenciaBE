package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plan_materiaofrecida")
@Access(AccessType.FIELD)
public class PlanMateriaOfrecida extends ParentEntity{

	private static final long serialVersionUID = 4086822680896067945L;
	
	@Column(name = "imagenplan", nullable = false)
	private String documentacion;

	public String getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}
	
	

}
