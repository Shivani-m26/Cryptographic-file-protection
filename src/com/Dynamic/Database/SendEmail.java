package com.Dynamic.Database;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class SendEmail {
	final String senderEmailId = "java.strydo@gmail.com";
	final String senderPassword = "strydo2021";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "587";
	String reciverEmailId = null;
	static String emailSubject = "OTP Verification";
	static String emailBody = "Your OTP Password :   983454";
	
	public SendEmail(String reciverEmailID, String emailSubject, String emailBody) {
		this.reciverEmailId = reciverEmailID;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailId);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		SecurityManager security = System.getSecurityManager();
		try {
			Authenticator auth = new SMPTAuthenticator();
			Session session = Session.getInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(emailBody);
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailId));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(reciverEmailId));
			Transport.send(msg);
			System.out.println("Message Send Successfully");
		} catch(Exception mex) {
			mex.printStackTrace();
		}
	}
	public class SMPTAuthenticator extends javax.mail.Authenticator {
		 public PasswordAuthentication getPasswordAuthentication() {
			 return new PasswordAuthentication(senderEmailId, senderPassword);
		 }
	}
	

}
