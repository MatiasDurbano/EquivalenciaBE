package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "docente")
@Access(AccessType.FIELD)
public class Docente extends ParentEntity {

	private static final long serialVersionUID = -8959889448814764621L;

		@Column(name = "nombre", nullable = false, length = 45)
		private String nombre;
		
		@Column(name = "apellido", nullable = false, length = 45)
		private String apellido;
		
		@Column(name = "mail", length = 45)		
		private String mail;
		
		//Esto es para preguntar el tipo de usuario que es
		@Column(name = "tipo")
		private int tipo;
		
		@Column(name = "idusuario")
		private long usuarioId;

		@Column(name = "idinstituto")
		private long idInsituto;
		
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

		public Long getUsuarioId() {
			return usuarioId;
		}

		public void setUsuarioId(Long l) {
			this.usuarioId = l;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public Long getIdInsituto() {
			return idInsituto;
		}

		public void setIdInsituto(long idInsituto) {
			this.idInsituto = idInsituto;
		}
		
}
