package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.CarreraMaterias;
import com.equivalencia.equivalenciaBE.Model.Email;
import com.equivalencia.equivalenciaBE.Model.InstitutosUngs;
import com.equivalencia.equivalenciaBE.Model.MateriaModelAdmin;
import com.equivalencia.equivalenciaBE.Model.MateriasDocente;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateriasUngs;
import com.equivalencia.equivalenciaBE.Service.MateriaService;
import com.equivalencia.equivalenciaBE.Service.MateriasHasCarreraService;
import com.equivalencia.equivalenciaBE.Service.SolicitudHasMateriaOfrecidaService;
import com.equivalencia.equivalenciaBE.Service.SolicitudHasMateriaUngsService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.equivalencia.equivalenciaBE.dao.SolicitudOfrecimientoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired 
	protected MateriasHasCarreraService materiaHasCarreraService;
	
	@Autowired 
	protected DocenteController docenteController;
	
	@Autowired 
	protected CarreraController carreraController;
	
	
	protected ObjectMapper mapper;
	
	public Materia save(Materia materia) {
		return this.materiaService.save(materia);
	}
	
	
	public Materia getMateriaPorNombre(String materia) {
		return this.materiaService.getOne(materia);
	}
	
	//
	public Materia materiaPorId(long id) {
		return this.materiaService.findAll(id);
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


	public boolean existe(String nombre,String carrera) {
		List<Materia> ret = new ArrayList<Materia>();
		ret= this.materiaService.findAllMateriasPorNombre(nombre,carrera);
		if(ret.size()>=1) {
			return true;
		}
		return false;
		
	}
	
	@RequestMapping(value = "/materiasDocente", method = RequestMethod.POST)
	public String enviarCarreras(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		Email email= mapper.readValue(solicitudJson, Email.class);
		Docente docente = this.docenteController.encontrarPorEmail(email.getEmail());
		List<DocenteHasMaterias> materias= this.docenteController.encontrarMateriasDeDocente(docente.getId());
		
		List<String> ret=new ArrayList<String>();
		
		for(DocenteHasMaterias docenteHasMateria: materias) {
			ret.add(this.materiaService.findOne(docenteHasMateria.getSegundaClave()).getNombre());
		}
		
		MateriasDocente materiaDocente = new MateriasDocente();
		materiaDocente.setMaterias(ret);
		
		return this.mapper.writeValueAsString(materiaDocente);
	}
	
	@RequestMapping(value = "/actualizarDisponibilidadMateria", method = RequestMethod.POST)
	public String actualizarDisponibilidad(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		CarreraMaterias carreraMaterias = mapper.readValue(solicitudJson, CarreraMaterias.class);
		List<MateriaModelAdmin> materias= carreraMaterias.getMaterias();
		Carrera carrera = this.carreraController.getCarreraPorNombre(carreraMaterias.getCarrera());
		for(MateriaModelAdmin materia :materias) {
			Materia mat= this.getMateriaPorNombre(materia.getNombre());
			this.materiaHasCarreraService.actualizarMateriaDisponible(mat.getId(), carrera.getId(), materia.getDisponible());
		}
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"actualizado"));
	}



	public List<Docente> BuscarMateriasDeDocente(long id) {
		
		List<DocenteHasMaterias> docenteHas = new ArrayList<DocenteHasMaterias>();
		docenteHas=this.docenteController.encontrarDocentesDeMateria(id);
		List<Docente> docentes= new ArrayList<Docente>();
		for(DocenteHasMaterias docentehas: docenteHas) {
		
			Docente docente=this.docenteController.buscarPorId(docentehas.getPrimeraClave());
	
			docentes.add(docente);
		}
		
		return docentes;
		
	}


	public List<Materia> buscarMateriasPorInstituto(long id) {
		return this.materiaService.buscarMateriaPorInstituto(id);
	}


	public List<SolicitudHasMateriasUngs> getSolicitudHasMateriaUngsPorSolicitud(long id) {
		return this.solicitudHasMateriaUngs.obtenerPorSolicitud(id);
	}


	public Materia buscarPorSolicitud(long id) {
		return this.materiaService.buscarPorSolicitud(id);
	}



	
}
