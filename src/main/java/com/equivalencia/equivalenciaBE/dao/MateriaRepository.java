package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;

public interface MateriaRepository extends JpaRepository<Materia,Long> {

	@SuppressWarnings("unchecked")
	Materia save(Materia materia);
	
	@Query(value = "Select * FROM materias, instituto where instituto.nombre = :nombre and carrera.idinstituto = instituto.id ", nativeQuery = true)
	List<Materia> findAll(@Param("nombre")String carrera);

	@Query(value = "Select distinct * FROM materias where materias.nombre=:nombre", nativeQuery = true)
	List<Materia> getOne(@Param("nombre")String nombre);

	@Query(value = "Select * FROM materias, carrera,materias_has_carrera where carrera.nombre=:carrera and carrera.id=materias_has_carrera.idcarrera and materias_has_carrera.idmateria=materias.id and materias.nombre=:nombre", nativeQuery = true)
	List<Materia> findAllPorNombre(@Param("nombre")String nombre,@Param("carrera")String carrera);

	
	@Query(value = "Select * FROM materias where materias.id=:id", nativeQuery = true)
	Materia encontrarPorClave(@Param("id")long segundaClave);
	
	@Query(value = "Select * FROM materias where materias.nombre=:nombre", nativeQuery = true)
	Materia encontrarPorNombre(@Param("nombre")String nombre);

	@Query(value = "Select m.id, m.nombre, m.horas, m.idplan FROM materias m, carrera c, materias_has_carrera mhc where  c.idinstituto=:id and mhc.idcarrera=c.id and mhc.idmateria=m.id", nativeQuery = true)
	List<Materia> buscarMateriaPorInstituto(long id);

	@Query(value = "Select m.id, m.nombre, m.horas, m.idplan FROM materias m, materias_has_solicitudes mhs where  mhs.idsolicitud=:id and mhs.idmateria=m.id", nativeQuery = true)
	Materia buscarPorSolicitud(long id);

	

}
