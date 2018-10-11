package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.Admin;


public interface AdminService {

	Admin save(Admin docente);

	List<Admin> findAll();

	Admin findOne(Long id);
	
}
