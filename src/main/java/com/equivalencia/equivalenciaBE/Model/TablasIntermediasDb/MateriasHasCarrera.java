package com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "materias_has_carrera")
public class MateriasHasCarrera  implements Serializable {


	@EmbeddedId Keys id;
	
	 public MateriasHasCarrera () {
	 }
	 
	 public MateriasHasCarrera (long id1, long id2) {
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

	 	@Column(name = "idmateria")
	 	private long primary;
	 	
	 	@Column(name = "idcarrera")
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
