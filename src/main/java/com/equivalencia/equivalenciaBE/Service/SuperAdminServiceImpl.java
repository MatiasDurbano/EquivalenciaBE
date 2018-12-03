package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SuperAdmin;
import com.equivalencia.equivalenciaBE.dao.SolicitudHasMateriaRepository;
import com.equivalencia.equivalenciaBE.dao.SuperAdminRepository;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	@Autowired
	protected SuperAdminRepository superAdminRepository;
	
	@Override
	public SuperAdmin buscarPorEmailSuperAdmin(String email) {
		return this.superAdminRepository.superAdminRepositorybuscarPorEmail(email);
	}

	@Override
	public SuperAdmin ObtenerSuperAdmin(long id) {
		return this.superAdminRepository.obtenerSuperAdmin(id);
	}

}
