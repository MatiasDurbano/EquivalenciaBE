package com.equivalencia.equivalenciaBE.Service;

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
	
	
}
