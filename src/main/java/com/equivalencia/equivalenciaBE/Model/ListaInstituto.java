package com.equivalencia.equivalenciaBE.Model;

import java.util.ArrayList;
import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ListaInstituto {
	
	
	@JsonDeserialize(as=ArrayList.class, contentAs=InstitutoPost.class)
	private List<InstitutoPost> institutos;

	public List<InstitutoPost> getInstitutos() {
		return institutos;
	}

	public void setInstitutos(List<InstitutoPost> institutos) {
		this.institutos = institutos;
	}
	

}
