package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@SuppressWarnings("unchecked")
	Usuario save(Usuario usuario);
}
