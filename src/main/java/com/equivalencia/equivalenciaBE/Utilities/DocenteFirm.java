package com.equivalencia.equivalenciaBE.Utilities;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Docente;
import com.equivalencia.equivalenciaBE.Model.TablasDb.Usuario;

public class DocenteFirm {
	
	private String nombre;
	private String apellido;
	private int privilegio;
	private int usuarioId;
	private String username;
	private String password;
	private String tipo;
	private String mail;
	private long instituto;
	
	
	//asco
	public Docente buildDocente() {
		Docente docente = new Docente();
		docente.setNombre(nombre);
		docente.setApellido(apellido);
		docente.setTipo(1);
		docente.setMail(mail);
		docente.setIdInsituto(instituto);
		return docente;
	}
	
	public Usuario buildUsuario(Usuario usuario) {

		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setTipo("Docente");
		return usuario;
	}
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apelido) {
		this.apellido = apelido;
	}
	public int getPrivilegio() {
		return privilegio;
	}
	public void setPrivilegio(int privilegios) {
		this.privilegio = privilegios;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getInstituto() {
		return instituto;
	}

	public void setInstituto(long instituto) {
		this.instituto = instituto;
	}
}
