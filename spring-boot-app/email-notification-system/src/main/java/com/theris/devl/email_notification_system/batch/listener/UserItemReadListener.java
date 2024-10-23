package com.theris.devl.email_notification_system.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.theris.devl.email_notification_system.batch.entity.AppUsers;

public class UserItemReadListener implements ItemReadListener<AppUsers> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserItemReadListener.class);

	@Override
	public void beforeRead() {
		LOGGER.info("Starting to read an item...");
	}

	@Override
	public void afterRead(AppUsers user) {
		// Log the read User object
		LOGGER.info("Successfully read User: {}", user);
	}

	@Override
	public void onReadError(Exception ex) {
		LOGGER.error("Error occurred while reading the item", ex);
	}
}
