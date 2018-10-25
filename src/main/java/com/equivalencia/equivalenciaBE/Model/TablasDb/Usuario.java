package com.equivalencia.equivalenciaBE.Model.TablasDb;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.equivalencia.equivalenciaBE.Utilities.RestResponse;

@Entity
@Table(name = "usuario")
@Access(AccessType.FIELD)
public class Usuario extends ParentEntity  {

	private static final long serialVersionUID = -1700100403942817588L;

	@Column(name = "username", nullable = false, length = 45)
	private String username;
	
	@Column(name = "password", nullable = false, length = 45)
	private String password;
	
	@Column(name = "tipo", nullable = false, length= 7)
	private String tipo;

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
	
	@Override
    public boolean equals(Object o) { 
  
        if (o == this) { 
            return true; 
        } 

        if (!(o instanceof Usuario)) { 
            return false; 
        } 

        Usuario c = (Usuario) o; 
        
        if(	this.getPassword().equals(c.getPassword())
        		&& this.getUsername().equals(c.getUsername()))
        			return true;
        
        return false; 
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	
}
