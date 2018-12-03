package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera,Long> {

	List<Carrera> findAll();

	@SuppressWarnings("unchecked")
	Carrera save(Carrera carrera);
	
	@Query(value = "Select * FROM carrera where carrera.id = :id ", nativeQuery = true)
	Carrera getOne(@Param("id")int idCarrera);

	@Query(value = "Select * FROM carrera, instituto where instituto.nombre = :nombre and carrera.idinstituto = instituto.id ", nativeQuery = true)
	List<Carrera> findAllporInstituto(@Param("nombre")String nombre);

	
	@Query(value = "Select * FROM carrera where carrera.nombre = :nombre ", nativeQuery = true)
	Carrera getOne(@Param("nombre")String carrera);

	@Query(value = "Select * FROM carrera where carrera.idinstituto = :id and carrera.disponible=1", nativeQuery = true)
	List<Carrera> buscarPorInstituto(@Param("id")long l);
	
	//repetido
	@Modifying
	@Transactional
	@Query(value = "update  carrera c set c.disponible=:disponible  where c.nombre = :nombre ", nativeQuery = true)
	void actualizarDisponiblidad(@Param("nombre")String carrera,@Param("disponible")long disponible);

	@Modifying
	@Transactional
	@Query(value = "update  carrera c set c.disponible=0  where c.nombre = :nombre ", nativeQuery = true)
	void borrarCarrera(@Param("nombre")String nombre);

	 

}
