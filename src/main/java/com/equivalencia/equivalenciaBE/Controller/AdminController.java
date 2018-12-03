package com.equivalencia.equivalenciaBE.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equivalencia.equivalenciaBE.Model.AdminModel;
import com.equivalencia.equivalenciaBE.Model.AdminPost;
import com.equivalencia.equivalenciaBE.Model.Administrador;
import com.equivalencia.equivalenciaBE.Model.AlumnoSolicitud;
import com.equivalencia.equivalenciaBE.Model.AsignaturaEquivalente;
import com.equivalencia.equivalenciaBE.Model.Email;
import com.equivalencia.equivalenciaBE.Model.SolicitudModel;
import com.equivalencia.equivalenciaBE.Model.SolicitudPost;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Comentario;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Instituto;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Solicitud;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SolicitudOfrecimiento;
import com.equivalencia.equivalenciaBE.Model.TablasDb.SuperAdmin;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Usuario;
import com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb.SolicitudHasMateria;
import com.equivalencia.equivalenciaBE.Service.AdminService;
import com.equivalencia.equivalenciaBE.Service.InstitutoService;
import com.equivalencia.equivalenciaBE.Service.SuperAdminService;
import com.equivalencia.equivalenciaBE.Utilities.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AdminController {

	@Autowired
	protected AdminService adminService;
	
	@Autowired
	protected InstitutoService institutoService;

	@Autowired
	protected SuperAdminService superAdminService;
	
	@Autowired
	protected UsuarioController usuarioController;
	
	@Autowired
	protected AlumnoController alumnoController;
	
	@Autowired
	protected MateriaController materiaController;
	
	@Autowired
	protected PlanController planController;
	
	@Autowired
	protected ComentarioController comentarioController;
	
	@Autowired
	protected SolicitudController solicitudController;
	
	protected ObjectMapper mapper;
	
	public Admin findOne(Long id) {
		return this.adminService.findOne(id);
	}
	
	public Admin save(Admin admin) {
		return this.adminService.save(admin);
	}
	
	@RequestMapping(value = "/obtenerAdmin", method = RequestMethod.POST)
	public String getInstitutos(@RequestBody String solicitudJson) throws IOException {
		
		this.mapper= new ObjectMapper();
		
		System.out.println(solicitudJson);
		
		Email email = this.mapper.readValue(solicitudJson, Email.class);
		
		Admin admin= this.adminService.buscarPorEmail(email.getEmail());
		
		AdminPost ret = new AdminPost();
		
		ret.setApellido(admin.getApelido());
		ret.setNombre(admin.getNombre());
		ret.setEmail(email.getEmail());
		ret.setInstituto(this.institutoService.getOne(admin.getIdInstituto()).getNombre());
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
		
	}
	
	@RequestMapping(value = "/obtenerSuperAdmin", method = RequestMethod.POST)
	public String getSuperAdmin(@RequestBody String solicitudJson) throws IOException {
		
		this.mapper= new ObjectMapper();
		
		Email email = this.mapper.readValue(solicitudJson, Email.class);
		
		SuperAdmin admin= this.superAdminService.buscarPorEmailSuperAdmin(email.getEmail());
		
		AdminPost ret = new AdminPost();
		
		ret.setApellido(admin.getApelido());
		ret.setNombre(admin.getNombre());
		ret.setEmail(email.getEmail());
		ret.setInstituto("");
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
		
	}
	
	@RequestMapping(value = "/guardarSuperAdmin", method = RequestMethod.POST)
	public String guardarSuperAdmin(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		AdminModel adminModel = this.mapper.readValue(solicitudJson, AdminModel.class);
		
		Usuario usuario = new Usuario();
		usuario.setTipo("Admin");
		usuario.setUsername(adminModel.getUsuario());
		usuario.setPassword(DigestUtils.md5Hex(adminModel.getContraseña()));
		usuario.setDisponible(1);
		if(!this.usuarioController.existe(usuario)) {
		
			Usuario user = this.usuarioController.guardarUsuario(usuario);
			
			Admin admin= new Admin();
			admin.setNombre(adminModel.getNombre());
			admin.setApelido(adminModel.getApellido());
			admin.setEmail(adminModel.getMail());
			System.out.println(adminModel.getInstituto());
			Instituto instituto= this.institutoService.getOne(adminModel.getInstituto());
			
			admin.setIdInstituto(instituto.getId());
			admin.setIdUsuario(user.getId());
			
			this.adminService.save(admin);
			
			return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"guardado"));
		}
		else {
			return this.mapper.writeValueAsString(new RestResponse(HttpStatus.CONFLICT.value(),"Usuario ya existente"));
				
		}
		
	}
	
	@RequestMapping(value = "/obtenerTodosLosAdmin", method = RequestMethod.GET)
	public String obtenerTodosLosAdmin() throws IOException {
		this.mapper= new ObjectMapper();
		List<Admin> admins = new ArrayList<Admin>();
		admins=this.adminService.findAll();
		List<AdminPost> adminsPost= new ArrayList<AdminPost>();
		for(Admin admin : admins) {
			AdminPost adm= new AdminPost();
			adm.setApellido(admin.getApelido());
			adm.setNombre(admin.getNombre());
			adm.setEmail(admin.getEmail());
			adm.setInstituto(this.institutoService.getOne(admin.getIdInstituto()).getNombre());
			adminsPost.add(adm);
		}
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),adminsPost));
	}
	
	@RequestMapping(value = "/traerTodasSolicitudes", method = RequestMethod.GET)
	public String traerTodasSolicitudes() throws IOException {
		this.mapper= new ObjectMapper();
		List<Alumno> alumnos= this.alumnoController.getAll();
		List<SolicitudPost> ret = new ArrayList<SolicitudPost>();

		for(Alumno alumno: alumnos) {
			AlumnoSolicitud alumnoSoli= this.solicitudController.getAlumnoSoli(alumno);
			List<SolicitudModel> SolicitudesModel=new ArrayList<SolicitudModel>();
			List<Solicitud> solicitudes = this.solicitudController.solicitudService.buscarSolicitudPorAlumno(alumno.getId());
				
			for(Solicitud solicitud : solicitudes) {
				
				SolicitudModel solicitudModel= new SolicitudModel();
				
				List<SolicitudHasMateria> materiasOfrecidas= new ArrayList<SolicitudHasMateria>();
				materiasOfrecidas=this.materiaController.getSolicitudHasMateria(solicitud.getId());
				
				solicitudModel.setmateriaUngs(this.materiaController.buscarPorSolicitud(solicitud.getId()).getNombre());
				List<AsignaturaEquivalente> ofrecimientos= new ArrayList<AsignaturaEquivalente>();
				
				//busco las materiasofrecidas
				for(SolicitudHasMateria materiaOfrecida :materiasOfrecidas) {
					
					AsignaturaEquivalente asignatura = new AsignaturaEquivalente();
						//id de las materias ofrecida
					SolicitudOfrecimiento solicitudOfrecimiento= this.materiaController.findMateriaOfrecimiento(materiaOfrecida.getSegundaClave());
					
						asignatura.setAnioAprobacion(solicitudOfrecimiento.getAnioAprobacion());	
						asignatura.setCargaHoraria(solicitudOfrecimiento.getCargaHoraria());
						asignatura.setNombre(solicitudOfrecimiento.getNombre());
						asignatura.setUniversidad(solicitudOfrecimiento.getUniversidad());
						asignatura.setDocumentacion(this.planController.getOneOfrecida(solicitudOfrecimiento.getIdPlan()));
					
						ofrecimientos.add(asignatura);
					}
									
				solicitudModel.setAsignaturaEquivalente(ofrecimientos);
				solicitudModel.setEstado(solicitud.getEstado());
				solicitudModel.setDisponible(solicitud.getDisponible());
				Comentario comentario = this.comentarioController.findComentario(solicitud.getComentario());
				solicitudModel.setComentario(comentario.getComentario());
				SolicitudesModel.add(solicitudModel);
			}
			SolicitudPost solicitudPost= new SolicitudPost();
			solicitudPost.setAlumnoSolicitud(alumnoSoli);
			solicitudPost.setsolicitudesModel(SolicitudesModel);
			
			ret.add(solicitudPost);
	}
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),ret));
	}

	@RequestMapping(value = "/traerTodosInstitutos", method = RequestMethod.GET)
	public String todosInstitutos() throws IOException {
		this.mapper= new ObjectMapper();
		
		List<Instituto> institutos= this.institutoService.findAll();
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),institutos));
	}
	
	@RequestMapping(value = "/borrarAdmin", method = RequestMethod.POST)
	public String borrar(@RequestBody String solicitudJson) throws IOException {
		this.mapper= new ObjectMapper();
		AdminPost adm= this.mapper.readValue(solicitudJson, AdminPost.class);
		Admin admin = this.adminService.buscarPorEmail(adm.getEmail());
		
		this.usuarioController.borrar(admin.getIdUsuario());
		
		return this.mapper.writeValueAsString(new RestResponse(HttpStatus.OK.value(),"borrado"));
	}
	

	public SuperAdmin obtenerSuperAdmin(long id) {
		return this.superAdminService.ObtenerSuperAdmin(id);
	}
	
	
	
}
