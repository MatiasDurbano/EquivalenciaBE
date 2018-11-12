package com.equivalencia.equivalenciaBE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;
import com.equivalencia.equivalenciaBE.Service.MateriaService;
import com.equivalencia.equivalenciaBE.Service.SolicitudHasMateriaOfrecidaService;
import com.equivalencia.equivalenciaBE.Service.SolicitudHasMateriaUngsService;
import com.equivalencia.equivalenciaBE.dao.SolicitudOfrecimientoRepository;

@RestController
public class MateriaController {

	@Autowired
	protected MateriaService materiaService;
	
	@Autowired
	protected SolicitudHasMateriaOfrecidaService solicitudHasMateriaOfrecida;
	
	@Autowired
	protected SolicitudOfrecimientoRepository solicitudOfrecimientoRepository;
	
	@Autowired 
	protected SolicitudHasMateriaUngsService solicitudHasMateriaUngs;
	
	
	public Materia getMateria(String materia) {
		return this.materiaService.getOne(materia);
	}


	public List<SolicitudHasMateriasUngs> getSolicitudHasMateriaUngs(long id) {
		return this.solicitudHasMateriaUngs.findAll(id);
	}

	public List<SolicitudHasMateria> getSolicitudHasMateria(long id) {
		return this.solicitudHasMateriaOfrecida.findAll(id);
	}


	public SolicitudOfrecimiento findMateriaOfrecimiento(long segundaClave) {
		return this.solicitudOfrecimientoRepository.getOne(segundaClave);
	}


	public void SolicitudHasMateriasUngs(long id, long id2) {
		SolicitudHasMateriasUngs solicitudHas = new SolicitudHasMateriasUngs(id,id2);
		this.solicitudHasMateriaUngs.save(solicitudHas);
	}
	
	
}
