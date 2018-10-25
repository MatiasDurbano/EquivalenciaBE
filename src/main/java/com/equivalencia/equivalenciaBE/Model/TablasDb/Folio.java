package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "folio")
@Access(AccessType.FIELD)
public class Folio extends ParentEntity{

	private static final long serialVersionUID = -4826350516622453830L;
	
	@Column(name = "codigo", nullable = false, length = 16)
	private String codigo;

	
	public Folio(String codigo) {
		this.codigo=codigo;
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	
}
