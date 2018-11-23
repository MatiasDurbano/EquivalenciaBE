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
import com.equivalencia.equivalenciaBE.Model.CarrerasMateriasPost;
import com.equivalencia.equivalenciaBE.Model.InstitutosUngs;
import com.equivalencia.equivalenciaBE.Model.MateriaModel;
import com.equivalencia.equivalenciaBE.Model.MateriaModelAdmin;
import com.equivalencia.equivalenciaBE.Model.PostCarreraMateria;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Carrera;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;
import com.equivalencia.equivalenciaBE.Model.TablasDb.PlanEstudio;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.MateriasHasCarrera;
import com.equivalencia.equivalenciaBE.Service.CarreraService;
import com.equivalencia.equivalenciaBE.Service.InstitutoService;
import com.equivalencia.equivalenciaBE.Service.MateriaService;
import com.equivalencia.equivalenciaBE.Service.MateriasHasCarreraService;
import com.equivalencia.equivalenciaBE.Service.SolicitudHasMateriaUngsService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CarreraController {

	@Autowired
	protected InstitutoService insitutoService;
	
	@Autowired 
	protected CarreraService carreraService;
	
	@Autowired 
	protected MateriaController materiaController;
	@Autowired 
	protected MateriasHasCarreraService materiaHas;
	@Autowired
	protected PlanController planController;
	
	protected ObjectMapper mapper;
	
	@RequestMapping(value = "/carrera", method = RequestMethod.GET)
	public String enviarCarreras() throws JsonProcessingException {
		this.mapper= new ObjectMapper();
		
		List<Instituto> institutos= this.insitutoService.findAll();
		List<Carrera> carreras = this.carreraService.findAll();
		List<InstitutosUngs> institutosCarreras = new ArrayList<InstitutosUngs>();
		
		for(Instituto instituto : institutos) {
			InstitutosUngs institutoCarrera = new InstitutosUngs();
			List<String> nombreCarrera= new ArrayList<String>();
			
			institutoCarrera.setNombre(instituto.getNombre());
			for(Carrera carrera: carreras) {
				if(carrera.getIdinstituto()==instituto.getId()) {
					nombreCarrera.add(carrera.getNombre());
				}
			}
			
			institutoCarrera.setCarreras(nombreCarrera);
			institutosCarreras.add(institutoCarrera);
		}
		return this.mapper.writeValueAsString(institutosCarreras);
	}

	
	
	@RequestMapping(value = "/carrerasmaterias", method = RequestMethod.POST)
	public String enviarCarrerasMaterias(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		
		Instituto instituto= this.mapper.readValue(solicitudJson, Instituto.class);
		
		System.out.println(instituto.getNombre());
		
		List<Carrera> Carreras= this.carreraService.findAllporInstituto(instituto.getNombre());
		
		//el return
		List<CarreraMaterias> materiaCarrera= new ArrayList<CarreraMaterias>();
	
		for(Carrera carrera: Carreras) {
			List<MateriasHasCarrera> materiaHas= this.findMateriasHasCarrera(carrera);
			
			CarreraMaterias carreraMateria = new CarreraMaterias();
			carreraMateria.setCarrera(carrera.getNombre());
			List<MateriaModelAdmin> materiaModelAdmin = new ArrayList<MateriaModelAdmin>();
			
			for(MateriasHasCarrera materia: materiaHas) {
				
				List<Materia> materias=this.materiaController.getAllMateria(materia.getPrimeraClave());
				
				//hora nombre;
				for(Materia mat: materias) {
					materiaModelAdmin.add(this.getMateriaModelAdmin(mat));	
				}
				
				carreraMateria.setMaterias(materiaModelAdmin);
				
				
			}
			materiaCarrera.add(carreraMateria);
			
		}
		PostCarreraMateria post = new PostCarreraMateria();
		post.setMateriaCarrera(materiaCarrera);
		String ret=this.mapper.writeValueAsString(materiaCarrera);
		return  ret;
		
	}
	
	@RequestMapping(value = "/guardarMaterias", method = RequestMethod.POST)
	public RestResponse guardarCarreraMaterias(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		CarrerasMateriasPost carreraMateria =  this.mapper.readValue(solicitudJson, CarrerasMateriasPost.class);
		List<CarreraMaterias> carreraMaterias = carreraMateria.getCarreraMaterias();
		Instituto instituto = this.insitutoService.getOne(carreraMateria.getInstituto().getNombre());
		
		for(CarreraMaterias materias: carreraMaterias) {
			Carrera carrera= this.carreraService.getOne(materias.getCarrera());
			List<MateriaModelAdmin> listaMaterias= materias.getMaterias();
			for(MateriaModelAdmin materiaModeladmin: listaMaterias) {
				if(!this.materiaController.existe(materiaModeladmin.getNombre(),materias.getCarrera())) {
					Materia materia=new Materia();
					materia.setHoras(materiaModeladmin.getHoras());
					materia.setNombre(materiaModeladmin.getNombre());
					//PRIMERO DEBO GUARDAR EL PLAN Y DESPUES ASIGNARLE EL ID// PARA CUANDO ESTE 
					PlanEstudio plan= new PlanEstudio();
					plan.setPlan(materiaModeladmin.getPlan());
					plan=this.planController.savePlanUngs(plan);
				
					materia.setPlan(plan.getId());
				
					materia=this.materiaController.save(materia);
					MateriasHasCarrera materiaHas= new MateriasHasCarrera(materia.getId(),carrera.getId());
				
					materiaHas=this.materiaHas.save(materiaHas);
					}
			}
			
		}
		
		return new RestResponse(HttpStatus.OK.value(),"ok");
		
	}
	
	
	
	
	private List<MateriasHasCarrera> findMateriasHasCarrera(Carrera carrera){
		List<MateriasHasCarrera> carreraMateria = this.materiaHas.findMateriHasCarrera(carrera.getId());
		return 	carreraMateria;

	}
	
	private List<Long> getIdsCarrera(List<MateriasHasCarrera> materiasHas){
		List<Long> ids= new ArrayList<Long>();
		
		for(MateriasHasCarrera materiaHas:materiasHas ) {
			ids.add(materiaHas.getSegundaClave());
		}
		
		return ids;
		
	}
	
	public Carrera getCarreraPorNombre(String carrera) {
		return this.carreraService.getOne(carrera);
	}

	public Carrera getOne(long l) {
		return this.carreraService.getOne(l);
	}
	private Carrera getCarrerabyID(List<Carrera> carreras, Long id) {
		Carrera ret= new Carrera();
		for(Carrera carrera: carreras) {
			System.out.println(carrera.getId() +" "+id);
			if(carrera.getId()==id) {
				ret=carrera;
				return ret;
			}
		}
		return ret;
	}
	
	
	
	private MateriaModelAdmin getMateriaModelAdmin(Materia materia) {
		MateriaModelAdmin materiaModel= new MateriaModelAdmin();
		materiaModel.setNombre(materia.getNombre());
		materiaModel.setHoras(materia.getHoras());
		return materiaModel;
	}
}
