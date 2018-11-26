package com.equivalencia.equivalenciaBE.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;


public interface SolicitudRepository extends JpaRepository<Solicitud,Long> {

	@SuppressWarnings("unchecked")
	Solicitud save(Solicitud solicitud);
	
	@Query(value = "insert into solicitudes_has_materia_ofrecida(idsolicitud,idmateriaofrecida) values(:id,:idofrecimiento)", nativeQuery = true)
	void solicitudHasMateriaOfrecidas(@Param("id")long id,@Param("idofrecimiento") long idofrecimiento);

	@Query(value = "select * from Solicitudes where Solicitudes.idfolio = :id", nativeQuery = true)
	List<Solicitud> findAll(@Param("id")long id);

	@Query(value = "select materias.nombre from materias, materias_has_solicitudes where materias_has_solicitudes.idsolicitud=:id and materias.id = materias_has_solicitudes.idmateria", nativeQuery = true)
	String findMateria(long id);
	
	@Query(value = "select * from materiaofrecida where materiaofrecida.id = :id", nativeQuery = true)
	SolicitudOfrecimiento findMateriaOfrecimiento(@Param("id") long id);
	
	@Query(value = "select * from solicitudes where solicitudes.id = :id", nativeQuery = true)
	Solicitud getOne(@Param("id")long id);

	@Query(value = "select * from solicitudes where solicitudes.id = :id and solicitudes.estado= 'en_espera'", nativeQuery = true)
	Solicitud getEnEspera(@Param("id")long segundaClave);

	
	@Query(value = "select s.id, s.idalumno, s.idfolio,s.estado, s.iddocente,s.idcomentario  from solicitudes s, materias_has_solicitudes mhs, materias m where s.idalumno=:id and m.nombre= :nombre and mhs.idmateria=m.id and mhs.idsolicitud=s.id", nativeQuery = true)
	Solicitud buscarPorAlumnoYmateriaUngs(@Param("id")long id,@Param("nombre") String getmateriaUngs);

	@Modifying
	@Transactional
	@Query(value = "update solicitudes s set s.Estado=:estado where s.id = :id", nativeQuery = true)	
	void actualizarEstado(@Param("id")long id,@Param("estado")String estado);

	
	@Query(value = "select * from solicitudes s where s.idalumno=:id and estado='en_espera'", nativeQuery = true)
	List<Solicitud> buscarPorAlumno(@Param("id")long id);

	
}
