package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;


public interface DocenteRepository extends JpaRepository<Docente, Long> {
	
	@SuppressWarnings("unchecked")
	Docente save(Docente docente);

	
	@Query(value = "Select * FROM docente where docente.idusuario= :id ", nativeQuery = true)
	Docente getOne(@Param("id")Long id);
	
	@Query(value = "Select * FROM docente where docente.id= :id ", nativeQuery = true)
	Docente getDocente(@Param("id")Long id);

	@Query(value = "Select * FROM docente where docente.nombre= :nombre and docente.apellido=:apellido ", nativeQuery = true)
	List<Docente> findAll(@Param("nombre")String nombre,@Param("apellido") String apellido);

	@Query(value = "Select * FROM docente where docente.mail= :email ", nativeQuery = true)
	Docente encontrarPorEMail(@Param("email")String email);

	@Query(value = "Select d.id,d.nombre,d.apellido,d.mail,d.tipo,d.idusuario,d.idinstituto FROM docente d, usuario u where d.idinstituto=:id and u.disponible=1 and u.id = d.idusuario ", nativeQuery = true)
	List<Docente> buscarPorInstituto(@Param("id")long id);


}
