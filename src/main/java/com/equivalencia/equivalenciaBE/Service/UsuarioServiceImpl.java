package com.equivalencia.equivalenciaBE.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.Usuario;
import com.equivalencia.equivalenciaBE.dao.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	protected UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return this.usuarioRepository.findAll();
	}
	
}
