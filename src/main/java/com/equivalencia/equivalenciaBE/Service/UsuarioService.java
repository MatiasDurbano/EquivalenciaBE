package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import com.equivalencia.equivalenciaBE.Model.Usuario;

public interface UsuarioService {

	Usuario save(Usuario usuario);

	List<Usuario> findAll();


}
