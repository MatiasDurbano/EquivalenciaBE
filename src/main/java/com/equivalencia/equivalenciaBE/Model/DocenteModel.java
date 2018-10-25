package com.equivalencia.equivalenciaBE.Model;

import java.util.List;



import com.equivalencia.equivalenciaBE.Model.TablasDb.Materia;


public class DocenteModel {
		
		private String nombre;
		
		private String apellido;
			
		private String mail;
			
		private List<Materia> materias;

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public List<Materia> getMaterias() {
			return materias;
		}

		public void setMaterias(List<Materia> materias) {
			this.materias = materias;
		}

}
