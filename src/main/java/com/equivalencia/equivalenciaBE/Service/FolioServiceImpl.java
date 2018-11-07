package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Folio;
import com.equivalencia.equivalenciaBE.dao.FolioRepository;

@Service
public class FolioServiceImpl implements FolioService {
	
	@Autowired
	protected FolioRepository folioRepository;
	
	@Override
	public Folio save(Folio folio) {
		return this.folioRepository.save(folio);
	}
	
	@Override
	public Folio getOne(String codigo) {
		return this.folioRepository.getOne(codigo);
	}

}
