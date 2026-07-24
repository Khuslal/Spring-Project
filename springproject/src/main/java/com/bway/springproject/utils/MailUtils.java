package com.bway.springproject.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
	private final JavaMailSender mailSender;

	MailUtils(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String toEmail, String subject, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toEmail);
		mail.setSubject(subject);
		mail.setText(message);
		
		mailSender.send(mail);
	}
}
