package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;
import com.equivalencia.equivalenciaBE.dao.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	protected AdminRepository adminRepository;
	
	@Override
	public Admin save(Admin docente) {
		return this.adminRepository.save(docente);
	}

	@Override
	public List<Admin> findAll() {
		return this.adminRepository.findAll();
	}

	@Override
	public Admin findOne(Long id) {
		return this.adminRepository.getOne(id);
	}
	

}
