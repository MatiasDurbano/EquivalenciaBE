package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;

public interface InstitutoService {
	
	List<Instituto> findAll();

	Instituto getOne(String  instituto);

	Instituto getOne(long id);

}
