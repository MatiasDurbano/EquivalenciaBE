package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {

	@Query(value = "Select * FROM superadministrador s where s.mail= :email ", nativeQuery = true)
	SuperAdmin superAdminRepositorybuscarPorEmail(@Param("email")String email);

	
	@Query(value = "Select * FROM superadministrador s where s.idusuario= :id ", nativeQuery = true)
	SuperAdmin obtenerSuperAdmin(@Param("id")long id);

}
