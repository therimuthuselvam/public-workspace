package com.theris.devl.email_notification_system.batch.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJobScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job emailNotificationJob;

	private Long jobCount = 0L;

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJobScheduler.class);

	// @Scheduled(cron = "0 0 9 * * ?") // Run every day at 9 AM
	@Scheduled(cron = "*/60 * * * * *") // Runs for every 1 minute
	public void runJob() {
		try {
			LOGGER.info("<----------------------->	emailNotificationJob is scheduled	<----------------------->");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("currentDate", dateFormat.format(new Date()))
					.addString("currentTime", timeFormat.format(new Date())).addLong("jobCount", ++jobCount)
					.toJobParameters();
			jobLauncher.run(emailNotificationJob, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
