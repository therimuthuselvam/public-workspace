package com.theris.devl.email_notification_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	public void sendEmail(SimpleMailMessage message) {
		LOGGER.debug("Executing sendEmail");
		javaMailSender.send(message);
		LOGGER.debug("Exit sendEmail");
	}
}
