package com.theris.devl.email_notification_system.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.theris.devl.email_notification_system.batch.entity.AppUsers;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class SQLReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SQLReader.class);

	@Bean
	public JpaPagingItemReader<AppUsers> reader(EntityManagerFactory entityManagerFactory) {
		LOGGER.debug("reader bean is created");
		JpaPagingItemReader<AppUsers> reader = new JpaPagingItemReader<>();
		reader.setEntityManagerFactory(entityManagerFactory);
		reader.setQueryString("SELECT u FROM AppUsers u WHERE u.notificationsEnabled = true");
		reader.setPageSize(10);
		return reader;
	}
}
