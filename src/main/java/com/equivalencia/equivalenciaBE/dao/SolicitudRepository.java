package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;


public interface SolicitudRepository extends JpaRepository<Solicitud,Long> {

	@SuppressWarnings("unchecked")
	Solicitud save(Solicitud solicitud);
	
	@Query(value = "insert into solicitudes_has_materia_ofrecida(idsolicitud,idmateriaofrecida) values(:id,:idofrecimiento)", nativeQuery = true)
	void solicitudHasMateriaOfrecidas(@Param("id")long id,@Param("idofrecimiento") long idofrecimiento);
}
