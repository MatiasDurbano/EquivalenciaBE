package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@SuppressWarnings("unchecked")
	Usuario save(Usuario usuario);

	@Query(value = "Select * FROM usuario where usuario.username = :username ", nativeQuery = true)
	List<Usuario> findAll(@Param("username")String username);

	@Modifying
	@Transactional
	@Query(value = "update usuario u set u.disponible=0 where u.id=:id ", nativeQuery = true)
	void borrar(@Param("id")long idUsuario);
		
	
}
