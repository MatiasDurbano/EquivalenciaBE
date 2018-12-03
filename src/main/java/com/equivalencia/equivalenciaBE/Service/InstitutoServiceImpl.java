package com.equivalencia.equivalenciaBE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.dao.InstitutoRepository;

@Service
public class InstitutoServiceImpl implements InstitutoService {

	@Autowired
	protected InstitutoRepository institutoRepository;
	
	
	public List<Instituto> findAll(){
		return this.institutoRepository.findAll();
	}


	@Override
	public Instituto getOne(String instituto) {
		return this.institutoRepository.getOne(instituto);
	}


	@Override
	public Instituto getOne(long id) {
		return this.institutoRepository.getOne(id);
	}


	@Override
	public boolean existe(String nombre) {
		Instituto instituto = this.institutoRepository.getOne(nombre);
		if(instituto==null) {
			return false;
		}
		return true;
	}


	@Override
	public Instituto guardar(Instituto instituto) {
		return this.institutoRepository.save(instituto);
	}


	@Override
	public void borrarInstituto(String nombre) {
		this.institutoRepository.borrarInstituto(nombre);
		
	}
	
	
}
