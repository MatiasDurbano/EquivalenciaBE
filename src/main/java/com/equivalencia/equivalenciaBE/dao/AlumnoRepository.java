package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;



public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

	@SuppressWarnings("unchecked")
	Alumno save(Alumno alumno);
	
	@Query(value = "Select * FROM alumnos where alumnos.id = :id ", nativeQuery = true)
	Alumno getOne(@Param("id")long id);

	@Query(value = "Select * FROM alumnos where alumnos.legajo = :legajo or alumnos.email=:email ", nativeQuery = true)
	List<Alumno> findAll(@Param("legajo")String legajo,@Param("email")String email);

	@Query(value = "Select * FROM alumnos where alumnos.email = :email ", nativeQuery = true)
	Alumno buscarPorEmail(@Param("email")String email);

	@Query(value = "Select a.id, a.nombre, a.apellido, a.email, a.idcertificados, a.dni, a.telefono, a.idcarrera, a.legajo  FROM alumnos a, carrera c where c.idinstituto = :id and a.idcarrera=c.id ", nativeQuery = true)
	List<Alumno> traerPorInstituto(long id);

}
