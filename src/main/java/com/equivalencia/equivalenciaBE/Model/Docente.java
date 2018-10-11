package com.equivalencia.equivalenciaBE.Model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "docente")
@Access(AccessType.FIELD)
public class Docente extends ParentEntity {

	private static final long serialVersionUID = 1421183026650158379L;

		@Column(name = "nombre", nullable = false, length = 45)
		private String nombre;
		
		@Column(name = "apellido", nullable = false, length = 45)
		private String apelido;
		
		@Column(name = "tipo")
		private int tipo;
		
		@Column(name = "usuarioId")
		private int usuarioId;

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

		public int getUsuarioId() {
			return usuarioId;
		}

		public void setUsuarioId(int usuarioId) {
			this.usuarioId = usuarioId;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}
		
}
