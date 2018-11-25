package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;

public interface DocenteHasMateriasRepository extends JpaRepository<DocenteHasMaterias,Long> {

	
	@SuppressWarnings("unchecked")
	DocenteHasMaterias save(DocenteHasMaterias docenteHasMaterias);
	
	@Query(value = "select * from docente_has_materias where docente_has_materias.iddocente = :id", nativeQuery = true)
	List<DocenteHasMaterias> MateriasDeDocente(@Param("id")long docente);

	@Query(value = "select * from docente_has_materias where docente_has_materias.idmaterias = :id", nativeQuery = true)
	List<DocenteHasMaterias> DocenteDeMateria(@Param("id")long materia);

	@Query(value = "delete from docente_has_materias where docente_has_materias.iddocente = :id", nativeQuery = true)
	void borrarMateriasDocente(long id);


}
