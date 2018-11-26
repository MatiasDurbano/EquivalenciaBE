package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@SuppressWarnings("unchecked")
	Admin save(Admin admin);

	
	@Query(value = "Select * FROM Admin where Admin.idusuario= :id ", nativeQuery = true)
	Admin getOne(@Param("id")Long id);

	@Query(value = "Select * FROM Admin where Admin.mail= :email ", nativeQuery = true)
	Admin buscarPorEmail(@Param("email")String email);
}