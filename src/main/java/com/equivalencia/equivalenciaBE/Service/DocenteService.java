package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;

public interface DocenteService {

	Docente save(Docente docente);

	List<Docente> findAll();

	Docente findOne(Long id);

	boolean existe(Docente docente);
}
