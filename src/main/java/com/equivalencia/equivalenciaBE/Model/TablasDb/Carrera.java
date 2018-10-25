package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carrera")
@Access(AccessType.FIELD)
public class Carrera extends ParentEntity {

	private static final long serialVersionUID = -5117779122315568104L;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "idUniversidad")
	private int idUniversidad;

}
