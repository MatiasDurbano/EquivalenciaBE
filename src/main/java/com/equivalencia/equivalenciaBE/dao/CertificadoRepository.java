package com.equivalencia.equivalenciaBE.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;

public interface CertificadoRepository  extends JpaRepository<Certificado,Long> {

	
	@SuppressWarnings("unchecked")
	Certificado save(Certificado certificado);
	
}
