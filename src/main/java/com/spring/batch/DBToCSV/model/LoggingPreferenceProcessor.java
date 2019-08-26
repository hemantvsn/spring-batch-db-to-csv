package com.spring.batch.DBToCSV.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LoggingPreferenceProcessor implements ItemProcessor<Preference, Preference> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingPreferenceProcessor.class);

	@Override
	public Preference process(Preference item) throws Exception {
		LOGGER.info("Processing Preference information: {}", item);
		return item;
	}
}
