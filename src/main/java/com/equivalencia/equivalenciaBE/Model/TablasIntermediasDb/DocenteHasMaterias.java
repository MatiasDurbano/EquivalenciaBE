package com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "docente_has_materias")
public class DocenteHasMaterias {

	@EmbeddedId Keys id;
	
	 public DocenteHasMaterias () {
	 }
	 
	 public DocenteHasMaterias (long id1, long id2) {
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

	 	@Column(name = "iddocente")
	 	private long primary;
	 	
	 	@Column(name = "idmaterias")
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
