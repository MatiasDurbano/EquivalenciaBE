package com.equivalencia.equivalenciaBE.Utilities;

import java.util.Random;

public class Codificador {
	
	public static String getAlfanumerico() {
		Random aleatorio = new Random();
        String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String cadena = "";
        int numero;
        int forma;
        
      //Método para el Cálculo de Código//

        forma=(int)(aleatorio.nextDouble() * alfa.length()-1);
        numero=(int)(aleatorio.nextDouble() * 99+100);
        cadena=cadena+alfa.charAt(forma)+numero;
        
        forma=(int)(aleatorio.nextDouble() * alfa.length()-1);
        numero=(int)(aleatorio.nextDouble() * 99+100);
        cadena=cadena+alfa.charAt(forma)+numero;
        
        forma=(int)(aleatorio.nextDouble() * alfa.length()-1);
        numero=(int)(aleatorio.nextDouble() * 99+100);
        cadena=cadena+alfa.charAt(forma)+numero;
        
        forma=(int)(aleatorio.nextDouble() * alfa.length()-1);
        numero=(int)(aleatorio.nextDouble() * 99+100);
        cadena=cadena+alfa.charAt(forma)+numero;
		
        return cadena;
		
	}

}
