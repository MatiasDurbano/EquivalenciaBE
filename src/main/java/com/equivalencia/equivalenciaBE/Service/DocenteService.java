package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;

public interface DocenteService {

	Docente save(Docente docente);

	List<Docente> findAll();

	Docente buscarPorUsuario(Long id);

	boolean existe(Docente docente);

	Docente buscarPorId(Long id);
	
	Docente encontrarPorMail(String email);

	List<Docente> buscarPorInstituto(long l);

}
