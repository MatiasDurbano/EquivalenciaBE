package com.equivalencia.equivalenciaBE.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;
import com.equivalencia.equivalenciaBE.dao.CertificadoRepository;

@Service
public class CertificadoServiceImpl implements CertificadoService {

	@Autowired
	protected CertificadoRepository certificadoRepository;
	
	@Override
	public Certificado save(Certificado certificado) {
		return this.certificadoRepository.save(certificado);
	}

	@Override
	public Certificado getOne(long idCertificados) {
		return this.certificadoRepository.getOne(idCertificados);
	}

	
}
