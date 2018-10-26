package com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes_has_materia_ofrecida")
public class SolicitudHasMateria implements Serializable {

	 @EmbeddedId Keys id;
	 
	 
	 public SolicitudHasMateria () {
	 }
	 
	 public SolicitudHasMateria (long id1, long id2) {
		 id= new Keys(id1,id2);
	 }
	 
	 public long getPrimeraClave() {
		   return this.id.getPrimary();
	  }
	   
	 public long getSegundaClave() {
		   return this.id.getSecond();
	 }
	 
	 @Embeddable
	 static class Keys implements Serializable{

	 	@Column(name = "idsolicitud")
	 	private long primary;
	 	
	 	@Column(name = "idmateriaofrecida")
	 	private long second;

	 	
	 	public Keys() {}
	 	public Keys(long id1, long id2) {
	 		this.primary = id1;
	 		this.second = id2;
	 		
	 	}
	 	
	 	public long getPrimary() {
	 		return primary;
	 	}


	 	public long getSecond() {
	 		return second;
	 	}

	 	
	 }
	
}
