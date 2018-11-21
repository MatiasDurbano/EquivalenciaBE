package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plan_estudio")
@Access(AccessType.FIELD)
public class PlanEstudio  extends ParentEntity {
	
	private static final long serialVersionUID = 1565574786249184809L;
	
	@Column(name = "imagenplan")
	private String plan;

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}
