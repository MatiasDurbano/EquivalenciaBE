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

import com.equivalencia.equivalenciaBE.Model.InstitutoPost;
import com.equivalencia.equivalenciaBE.Model.ListaInstituto;
import com.equivalencia.equivalenciaBE.Model.MateriaPost;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Usuario;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.Service.InstitutoService;
import com.equivalencia.equivalenciaBE.Service.MateriaService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class InstitutoController {
	
	@Autowired
	protected InstitutoService insitutoService;
	
	@Autowired
	protected MateriaController materiaController;
	
	@Autowired
	protected CarreraController carreraController;
	
	protected ObjectMapper mapper;
	
	public Instituto obtenerInstitutoPorNombre(String nombre) {
		return this.insitutoService.getOne(nombre);
	}
	
	@RequestMapping(value = "/institutos", method = RequestMethod.GET)
	public String getInstitutos() throws JsonProcessingException {
		this.mapper= new ObjectMapper();
		this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		List<Instituto> ret = this.insitutoService.findAll();
		return this.mapper.writeValueAsString(ret);
		
	}
	
	@RequestMapping(value = "/agregarInstituto", method = RequestMethod.POST)
	public String agregarInstituto(@RequestBody String usuarioJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		ListaInstituto institutos= mapper.readValue(usuarioJson, ListaInstituto.class);
	      
		for(InstitutoPost instituto: institutos.getInstitutos()) {
			Instituto inst= new Instituto();
			inst.setNombre(instituto.getNombre());
			inst.setDisponible(1);
			
			this.insitutoService.guardar(inst);
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"agregados"));
		
	}
	
	@RequestMapping(value = "/borrarInstituto", method = RequestMethod.POST)
	public String borrarInstituto(@RequestBody String usuarioJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		ListaInstituto institutos= mapper.readValue(usuarioJson, ListaInstituto.class);
      
		for(InstitutoPost instituto: institutos.getInstitutos()) {
			this.insitutoService.borrarInstituto(instituto.getNombre());
		}
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"borrados"));
		
	}
	
	//revisar metodos
	@RequestMapping(value = "/materiasPorInstituto", method = RequestMethod.POST)
	public String getMateriasPorInstituto(@RequestBody String usuarioJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		//Instituto inst= this.mapper.readValue(usuarioJson,Instituto.class);
		Instituto instituto= this.obtenerInstitutoPorNombre(usuarioJson);
		
		MateriaPost ret = new MateriaPost();
		List<String> materasPost=new ArrayList<String>();
		if(this.carreraController.buscarPorInstituto(instituto.getId())!=null) {
		List<Carrera> carreras = this.carreraController.buscarPorInstituto(instituto.getId());
		
		for(Carrera carrera: carreras) {
			List<MateriasHasCarrera> materiasHas= this.carreraController.materiaHas.findMateriHasCarreraPorIdCarrera(carrera.getId());
			for(MateriasHasCarrera materiaHas: materiasHas) {
				Materia materias=this.materiaController.materiaPorId(materiaHas.getPrimeraClave());
				materasPost.add(materias.getNombre());
				
					}
			
				}
		
		ret.setMaterias(materasPost);
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
	}
	
}
