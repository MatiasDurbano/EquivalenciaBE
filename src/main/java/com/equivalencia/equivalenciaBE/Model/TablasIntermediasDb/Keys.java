package com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Keys implements Serializable{

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
