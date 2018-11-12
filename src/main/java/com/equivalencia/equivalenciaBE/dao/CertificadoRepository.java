package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;

public interface CertificadoRepository  extends JpaRepository<Certificado,Long> {

	
	@SuppressWarnings("unchecked")
	Certificado save(Certificado certificado);
	
	@Query(value = "Select * FROM certificados where certificados.id = :id ", nativeQuery = true)
	Certificado getOne(@Param("id")long id);
	
}
