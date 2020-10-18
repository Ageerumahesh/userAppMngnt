package com.user.util;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
    @Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to,String subject,String body) {
		
		MimeMessage mimeMsg =  mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
		boolean isSent = false;
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(mimeMsg);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
		
	}

}
