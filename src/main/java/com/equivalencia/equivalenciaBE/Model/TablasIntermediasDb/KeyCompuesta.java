package com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class KeyCompuesta implements Serializable {
	
   @EmbeddedId Keys id;
   
   public long getPrimeraClave() {
	   return this.id.getPrimary();
   }
   
   public long getSegundaClave() {
	   return this.id.getSecond();
   }
	
}
