package com.spring.batch.DBToCSV.config;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobScheduler {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobScheduler.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Scheduled(cron = "${scheduler.cron}")
	public void perform() throws Exception {
		LOG.info("*****************************************************");
		LOG.info("STARTING SCHEDULER");
		LOG.info("*****************************************************");
		Instant startTime = Instant.now();
		
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
		
		Instant finishTime = Instant.now();
		
		LOG.info("*****************************************************");
		LOG.info("ENDING SCHEDULER");
		LOG.info("*****************************************************");
		LOG.info("***** SCHEDULER COMPLETED JOB in {} ******", Duration.between(startTime, finishTime));
	}
}
