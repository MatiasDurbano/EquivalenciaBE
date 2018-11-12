package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "certificados")
@Access(AccessType.FIELD)
public class Certificado extends ParentEntity {

	private static final long serialVersionUID = 2884181919398995842L;
	
	@Column(name = "constanciadiciplinaria")	
	private String constancia;
	
	@Column(name = "analitico")	
	private String analitico;

	
	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public String getAnalitico() {
		return analitico;
	}

	public void setAnalitico(String analitico) {
		this.analitico = analitico;
	}
	
	
	
		
}
