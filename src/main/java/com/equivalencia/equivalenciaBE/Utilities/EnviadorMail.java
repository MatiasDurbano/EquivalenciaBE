package com.equivalencia.equivalenciaBE.Utilities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Alumno;
import com.equivalencia.equivalenciaBE.Properties.AbsolutFileSearch;
import com.equivalencia.equivalenciaBE.Properties.DataConfig;
import com.equivalencia.equivalenciaBE.Properties.PropertiesFileSearch;
import com.equivalencia.equivalenciaBE.Properties.PropertiesLoader;



public class EnviadorMail {
	
	
	private PropertiesLoader properties;
	private String email;
	private String clave;
	private DataConfig data;
	private Session session;
	private MimeMessage message; 
	private Properties props;
	
	public EnviadorMail() {
		this.properties = new PropertiesLoader();
		this.data = properties.getDataConfig();
		this.email=data.getEmail();		
		this.clave=data.getClave();
		
		props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", this.email);
	    props.put("mail.smtp.clave", this.clave);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    session = Session.getDefaultInstance(props);
	    message = new MimeMessage(session);

	}
	
	public void enviarAAlumno(String destinatario, String codigo) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String cuerpo="El codigo de seguimiento de solicitud es: "+codigo;
		String asunto="Codigo de seguimiento UNGS";
	    try {
	        message.setFrom(new InternetAddress(this.email));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", this.email, this.clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
	public void enviarADocente(String destinatario) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String cuerpo="Tiene una solicitud de equivalencia en el sistema";
		String asunto="Codigo de seguimiento UNGS";
	    try {
	    	System.out.println("envidado a: "+destinatario);
	        message.setFrom(new InternetAddress(this.email));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", this.email, this.clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}

	public void enviarEmailDeFaltante(String mail, Alumno alumno,String materia) {
		String cuerpo="El alumno "+alumno.getNombre()+" "+alumno.getApellido()+" con legajo: "+alumno.getLegajo()+" le falta una resolución para finalizar su solicitud de equivalencia y es en la materia "+ materia ;
		String asunto="Alumno terminando trámite";
	    try {
	    	message.setFrom(new InternetAddress(this.email));
	        message.addRecipients(Message.RecipientType.TO, mail);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", this.email, this.clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
		
	}
	
}
