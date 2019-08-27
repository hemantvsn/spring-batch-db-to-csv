package com.spring.batch.DBToCSV.config.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.DBToCSV.model.Preference;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ItemReader<Preference> reader;

	@Autowired
	private ItemWriter<Preference> writer;

	@Autowired
	private ItemProcessor<Preference, Preference> processor;

	@Bean
	public Step step() {

		Step step = stepBuilderFactory
				.get("DB_TO_CSV_STEP")
				.<Preference, Preference>chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
		
		LOG.info("********* BATCH STEP CREATED SUCCESSFULLY :{} ************", step);
		return step;
	}

	@Bean
	Job databaseToCsvFileJob(JobBuilderFactory jobBuilderFactory, Step step) {
		Job job = jobBuilderFactory
				.get("DB_TO_CSV_JOB")
				.incrementer(new RunIdIncrementer())
				.flow(step)
				.end()
				.build();
		
		LOG.info("********* BATCH JOB CREATED SUCCESSFULLY : {} ************", job);
		return job;
	}
}
