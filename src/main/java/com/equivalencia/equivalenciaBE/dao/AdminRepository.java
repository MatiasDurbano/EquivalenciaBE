package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@SuppressWarnings("unchecked")
	Admin save(Admin admin);

	@Query(value = "Select a.id, a.nombre, a.apellido, a.mail, a.tipo, a.idusuario, a.idinstituto FROM Admin a, usuario u where u.disponible=1 and a.idusuario=u.id ", nativeQuery = true)
	List<Admin> findAll();
	
	@Query(value = "Select * FROM Admin where Admin.idusuario= :id ", nativeQuery = true)
	Admin getOne(@Param("id")Long id);

	@Query(value = "Select * FROM Admin where Admin.mail= :email ", nativeQuery = true)
	Admin buscarPorEmail(@Param("email")String email);

	@Query(value = "Select * FROM superadministrador s where s.mail= :email ", nativeQuery = true)
	Admin buscarPorEmailSuperAdmin(@Param("email")String email);
}