package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera,Long> {

	List<Carrera> findAll();

	@Query(value = "Select * FROM carrera where carrera.id = :id ", nativeQuery = true)
	Carrera getOne(@Param("id")int idCarrera);

	@Query(value = "Select * FROM carrera, instituto where instituto.nombre = :nombre and carrera.idinstituto = instituto.id ", nativeQuery = true)
	List<Carrera> findAllporInstituto(@Param("nombre")String nombre);

	
	@Query(value = "Select * FROM carrera where carrera.nombre = :nombre ", nativeQuery = true)
	Carrera getOne(@Param("nombre")String carrera);

	 

}
