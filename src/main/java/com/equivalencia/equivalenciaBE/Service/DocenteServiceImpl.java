package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.dao.DocenteRepository;


@Service
public class DocenteServiceImpl  implements DocenteService {

	@Autowired
	protected DocenteRepository docenteRepository;

	@Override
	public Docente save(Docente docente) {
		return this.docenteRepository.save(docente);
	}

	@Override
	public List<Docente> findAll() {
		return this.docenteRepository.findAll();
	}
	
	@Override
	public Docente buscarPorUsuario(Long id) {
		return this.docenteRepository.getOne(id);
	}
	
	@Override
	public Docente buscarPorId(Long id) {
		return this.docenteRepository.getDocente(id);
	}


	@Override
	public boolean existe(Docente docente) {
		List<Docente> list=this.docenteRepository.findAll(docente.getNombre(),docente.getApellido());
		if(list.size()>=1) {
			return true;
		}
		return false;
	}

	@Override
	public Docente encontrarPorMail(String email) {
		return this.docenteRepository.encontrarPorEMail(email);
	}

	@Override
	public List<Docente> buscarPorInstituto(long id) {
		return this.docenteRepository.buscarPorInstituto(id);
	}

}
