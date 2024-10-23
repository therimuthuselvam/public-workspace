package com.theris.devl.email_notification_system.batch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.theris.devl.email_notification_system.service.EmailService;

@Component
public class EmailWriter implements ItemWriter<SimpleMailMessage> {

    @Autowired
    private EmailService emailService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailWriter.class);

	@Override
	public void write(Chunk<? extends SimpleMailMessage> messages) throws Exception {
        for (SimpleMailMessage message : messages) {
        	LOGGER.info("Executing writer");
        	emailService.sendEmail(message);
        }
		
	}
}
