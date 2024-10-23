package com.theris.devl.email_notification_system.batch.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;

import com.theris.devl.email_notification_system.batch.entity.AppUsers;
import com.theris.devl.email_notification_system.batch.listener.JobCompletionNotificationListener;
import com.theris.devl.email_notification_system.batch.listener.UserItemReadListener;
import com.theris.devl.email_notification_system.batch.processor.EmailProcessor;
import com.theris.devl.email_notification_system.batch.writer.EmailWriter;

@Configuration
public class BatchJobConfig {

	private final JobRepository jobRepository;

	private final PlatformTransactionManager transactionManager;

	private Logger LOGGER = LoggerFactory.getLogger(BatchJobConfig.class);

	BatchJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	public Step sendEmailStep(JpaPagingItemReader<AppUsers> reader, EmailProcessor processor, EmailWriter writer) {
		LOGGER.debug("sendEmailStep bean is created");
		StepBuilder stepBuilder = new StepBuilder("sendEmailStep", jobRepository);
		SimpleStepBuilder<AppUsers, SimpleMailMessage> step = stepBuilder.<AppUsers, SimpleMailMessage>chunk(10)
				.reader(reader).processor(processor).writer(writer).transactionManager(transactionManager)
				.listener(new UserItemReadListener());

		return step.build();
	}

	@Bean
	public Job emailNotificationJob(JpaPagingItemReader<AppUsers> reader, EmailProcessor processor,
			EmailWriter writer) {
		LOGGER.debug("emailNotificationJob bean is created");
		JobBuilder jobBuilder = new JobBuilder("emailNotificationJob", jobRepository);
		SimpleJobBuilder job = jobBuilder.start(sendEmailStep(reader, processor, writer))
				.listener(new JobCompletionNotificationListener());
		return job.build();
	}

}
