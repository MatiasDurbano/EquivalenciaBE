package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "certificado")
@Access(AccessType.FIELD)
public class Certificado extends ParentEntity {

	private static final long serialVersionUID = 2884181919398995842L;
	
	@Column(name = "constancia")	
	private byte[] constancia;
	
	@Column(name = "analitico")	
	private byte[] analitico;

	public Certificado(byte[] constancia, byte[] analitico) {
		this.constancia=constancia;
		this.analitico=analitico;
	}
	
	public byte[] getConstancia() {
		return constancia;
	}

	public void setConstancia(byte[] constancia) {
		this.constancia = constancia;
	}

	public byte[] getAnalitico() {
		return analitico;
	}

	public void setAnalitico(byte[] analitico) {
		this.analitico = analitico;
	}
	
	
	
		
}
