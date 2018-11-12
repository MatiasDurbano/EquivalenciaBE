package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Certificado;
import com.equivalencia.equivalenciaBE.Service.CertificadoService;

@Controller
public class CertificadoController {
	
	@Autowired
	protected CertificadoService certificadoService;
	
	
	public Certificado save(Certificado certificado) {
		return this.certificadoService.save(certificado);
		
	}


	public Certificado getOne(long idCertificados) {
		return this.certificadoService.getOne(idCertificados);
	}


	

}
