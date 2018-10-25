package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MateriaOfrecida")
@Access(AccessType.FIELD)
public class SolicitudOfrecimiento extends ParentEntity {
	
	private static final long serialVersionUID = -167353177980675614L;

	@Column(name = "universidad")
	private String universidad;
	
	@Column(name = "añoAprobacion")
	private int añoAprobacion;
	
	@Column(name = "materia", nullable = false, length = 45)
	private String asignaturaPropuesta;
	
	@Column(name = "horas")
	private int cargaHoraria;
	
	@Column(name = "idPlan")
	private int idPlan;
	
	public int getAñoAprobacion() {
		return añoAprobacion;
	}
	public void setAñoAprobacion(int añoAprobacion) {
		this.añoAprobacion = añoAprobacion;
	}
	public String getAsignaturaPropuesta() {
		return asignaturaPropuesta;
	}
	public void setAsignaturaPropuesta(String asignaturaPropuesta) {
		this.asignaturaPropuesta = asignaturaPropuesta;
	}
	public String getUniversidad() {
		return universidad;
	}
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}
	
	

}
