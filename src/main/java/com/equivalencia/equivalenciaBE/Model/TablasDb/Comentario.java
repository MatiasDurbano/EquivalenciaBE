package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comentario")
@Access(AccessType.FIELD)
public class Comentario extends ParentEntity {

	private static final long serialVersionUID = 8940439905468602231L;
	
	@Column(name = "comentario", nullable = true, length = 300)
	private String comentario;

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
