package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.Administrador;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;


public interface AdminService {

	Admin save(Admin docente);

	List<Admin> findAll();

	Admin findOne(Long id);

	Admin buscarPorEmail(String email);

	Admin buscarPorEmailSuperAdmin(String email);

	
}
