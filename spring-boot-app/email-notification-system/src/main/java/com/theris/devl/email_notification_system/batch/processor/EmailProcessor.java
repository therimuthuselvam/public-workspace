package com.theris.devl.email_notification_system.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.theris.devl.email_notification_system.batch.entity.AppUsers;

@Component
@StepScope
public class EmailProcessor implements ItemProcessor<AppUsers, SimpleMailMessage> {

	@Value("${mail.subject}")
	private String mailSubject;

	@Value("${mail.text}")
	private String mailText;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailProcessor.class);

	@Override
	public SimpleMailMessage process(AppUsers user) throws Exception {
		LOGGER.info("Executing processor");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject(mailSubject);
		message.setText(String.format(mailText, user.getFirstName()));
		return message;
	}
}
