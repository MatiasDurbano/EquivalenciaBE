package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Access(AccessType.FIELD)
public class Admin extends ParentEntity{

	private static final long serialVersionUID = -9066519226441645463L;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 45)
	private String apelido;
	
	@Column(name = "mail", nullable = false, length = 45)
	private String email;
	
	@Column(name = "tipo")
	private int tipo;
	
	@Column(name = "idusuario")
	private long idUsuario;
	
	@Column(name = "idinstituto")
	private long idInstituto;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdInstituto() {
		return idInstituto;
	}

	public void setIdInstituto(int idInstituto) {
		this.idInstituto = idInstituto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
