package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@SuppressWarnings("unchecked")
	Admin save(Admin admin);

}
