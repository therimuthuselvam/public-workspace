package com.theris.devl.email_notification_system.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("Job is started with parameters: {}", jobExecution.getJobParameters().toString());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("Job is completed with parameters: {}", jobExecution.getJobParameters().toString());
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			LOGGER.info("Job is failed with parameters: {}", jobExecution.getJobParameters().toString());
		}
	}
}
