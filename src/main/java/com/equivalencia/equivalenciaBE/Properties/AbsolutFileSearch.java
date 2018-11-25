package com.equivalencia.equivalenciaBE.Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class AbsolutFileSearch implements PropertiesFileSearch {
	
	private String ruta = "src/main/java/com/equivalencia/equivalenciaBE/Properties/EmailConfig.properties";
	
	public AbsolutFileSearch() {
	}			
	
	public FileReader getFileAddress() throws FileNotFoundException	{
		FileReader ret = new FileReader(ruta);
		return ret;
	}
	public void setAddress(String nuevaRuta) {
		this.ruta = nuevaRuta;
	}
}

