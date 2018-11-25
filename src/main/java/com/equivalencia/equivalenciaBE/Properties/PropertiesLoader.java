package com.equivalencia.equivalenciaBE.Properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	private PropertiesFileSearch fileSercher;
	private Properties properties;
	private DataConfig data;
	
	public PropertiesLoader() {
		
		this.fileSercher = new AbsolutFileSearch();
		this.properties = new Properties();
		this.data = new DataConfig();
		this.readProperties();
	}
	
	public void readProperties() {
		try {
			FileReader file = fileSercher.getFileAddress();
			properties.load(file);			
			this.readEmail();
			this.readClave();
			
			}
		catch (IOException e) {
			System.out.println("no se puede leer el archivo properties");
		}
	}
	
	public void readEmail() {
		String email = properties.getProperty("email");
		if(email == null) 	
			System.out.println("Email no definido");				
		data.setEmail(email);
		
	}
	
	public void readClave() {
		String clave = properties.getProperty("clave");
		if(clave == null) 	
			System.out.println("Clave no definido");
        data.setClave(clave);   
      
      
	}
	
	 
	public DataConfig getDataConfig() {
		return data;
	}
	
	public void setAdress(String arg0) {
		fileSercher.setAddress(arg0);
		readProperties();
	}
}
