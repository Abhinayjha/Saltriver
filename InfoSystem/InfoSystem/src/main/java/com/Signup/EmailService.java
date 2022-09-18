package com.Signup;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.ws.mime.MimeMessage;

@Service
public class EmailService {
	
	public boolean emailservice(String subject,String message,String to)
	{
		//rest of code
		boolean f=false;
		String from="jhaabhinay81@gmail.com";
		
		//variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties pro=System.getProperties();
		System.out.println("Properties:"+pro);
		
		//setting important information to properties
		//host set
		pro.put("mail.smtp.host", host);
		pro.put("mail.smtp.port", "465");
		pro.put("mail.smtp.ssl.enable", "true");
		pro.put("mail.smtp.auth", "true");
		
		//step1: To get the session object
		Session sess=Session.getInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Uname:"jhaa25300@gmail.com",Password:"ABhi1920@");
			}
		});
		sess.setDebug(true);
		
		//
		MimeMessage m=new MimeMessage(sess);
		
		try {
			//from email
			m.setFrom(from);
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//adding subject to message
			m.addSubject(subject);
			//adding text to message
			m.addText(message);
			
			//send
			//step3: send the message using transport class
			Transport.sent(m);
			System.out.println("Send to email..............");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
