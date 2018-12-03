package com.equivalencia.equivalenciaBE.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.SuperAdmin;

public interface SuperAdminService {

	SuperAdmin buscarPorEmailSuperAdmin(String email);

	SuperAdmin ObtenerSuperAdmin(long id);

}
