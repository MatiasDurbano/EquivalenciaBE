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

import com.equivalencia.equivalenciaBE.Model.CodigoAlumno;
import com.equivalencia.equivalenciaBE.Model.DocentePost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.DocenteHasMaterias;
import com.equivalencia.equivalenciaBE.Service.DocenteHasMateriasService;
import com.equivalencia.equivalenciaBE.Service.DocenteService;
import com.equivalencia.equivalenciaBE.Utilities.DocenteFirm;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DocenteController {
	
	
	@Autowired
	protected DocenteService docenteService;
	
	@Autowired
	protected DocenteHasMateriasService docenteHasMaterias;
	
	@Autowired
	protected MateriaController materiaController;
	
	@Autowired
	protected InstitutoController institutoController;
	
	protected ObjectMapper mapper;
	
	
	public Docente buscarPorUsuario(Long id) {
		return this.docenteService.buscarPorUsuario(id);
	}
	
	public Docente buscarPorId(Long id) {
		return this.docenteService.buscarPorId(id);
	}
	
	public Docente save(Docente docente) {
		return this.docenteService.save(docente);
	}

	public Docente buildDocente(DocenteFirm docenteFirm) {
		Docente ret = docenteFirm.buildDocente();
		return ret;
	}

	public Docente guardar(Docente docente, long id) {
		docente.setUsuarioId(id);
		return this.docenteService.save(docente);
	}

	public boolean existe(Docente docente) {
		return this.docenteService.existe(docente);
	}

	public Docente encontrarPorEmail(String email) {
		return this.docenteService.encontrarPorMail(email);
	}
	
	public List<DocenteHasMaterias> encontrarMateriasDeDocente(long docente) {
		return this.docenteHasMaterias.encontrarMateriasDeDocente(docente);
	}

	public List<DocenteHasMaterias> encontrarDocentesDeMateria(long materia) {
		return this.docenteHasMaterias.encontrarDocentesDeMateria(materia);
	}
	
	@RequestMapping(value = "/actualizarDocente", method = RequestMethod.POST)
	public String actualizarDocente(@RequestBody String stringJson) throws JsonProcessingException {
		try {
			this.mapper= new ObjectMapper();		

			DocentePost docentePost=this.mapper.readValue(stringJson,DocentePost.class);
			Docente docente= this.docenteService.encontrarPorMail(docentePost.getEmail());
			this.docenteHasMaterias.borrarMateriasDocente(docente.getId());
			
			List<String> materias = docentePost.getasignaturas();
			
			for(String materia: materias) {
				Materia mater = this.materiaController.getMateriaPorNombre(materia);
				DocenteHasMaterias docenteHasMateria= new DocenteHasMaterias(docente.getId(),mater.getId());
				this.docenteHasMaterias.save(docenteHasMateria);
			}
					
			return this.mapper.writeValueAsString(new RestResponse(HttpStatus.ACCEPTED.value(),"acepta"));
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.NOT_FOUND.value(),"No encontrado"));
		
	}

	@RequestMapping(value = "/traerDocentes", method = RequestMethod.POST)
	public String getTraerDocente(@RequestBody String stringJson) throws JsonProcessingException {
		try {
			this.mapper= new ObjectMapper();
			
			Instituto instituto= this.mapper.readValue(stringJson, Instituto.class);
			
			Instituto inst= this.institutoController.obtenerInstitutoPorNombre(instituto.getNombre());
			
			List<Docente> docentes = this.docenteService.buscarPorInstituto(inst.getId());
			List<DocentePost> docentesPost = new ArrayList<DocentePost>();
			
			for(Docente docente: docentes) {
				DocentePost docentePost= new DocentePost();
				docentePost.setEmail(docente.getMail());
				docentePost.setNombre(docente.getNombre());
				docentePost.setApellido(docente.getApellido());
				
				List<DocenteHasMaterias> docenteHas = new ArrayList<DocenteHasMaterias>();
				List<String> nombreMaterias= new ArrayList<String>();
				docenteHas=this.docenteHasMaterias.encontrarMateriasDeDocente(docente.getId());
				
				for(DocenteHasMaterias docenteMateria: docenteHas) {
					Materia mat= this.materiaController.materiaPorId(docenteMateria.getSegundaClave());
					nombreMaterias.add(mat.getNombre());
				}
				
				docentePost.setasignaturas(nombreMaterias);
				docentesPost.add(docentePost);
				
			}
					
			return this.mapper.writeValueAsString(new RestResponse(HttpStatus.ACCEPTED.value(),docentesPost));
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.CONFLICT.value(),"Algo salio mal"));
		
	}
		
	

	
}
