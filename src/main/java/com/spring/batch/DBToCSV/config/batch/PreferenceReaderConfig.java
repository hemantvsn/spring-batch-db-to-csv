package com.spring.batch.DBToCSV.config.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.DBToCSV.model.Preference;
import com.spring.batch.DBToCSV.model.PreferenceRowMapper;

@Configuration
public class PreferenceReaderConfig {

	private static final Logger LOG = LoggerFactory.getLogger(PreferenceReaderConfig.class);

	@Autowired
	private DataSource ds;

	@Bean
	public JdbcCursorItemReader<Preference> reader() {
		JdbcCursorItemReader<Preference> reader = new JdbcCursorItemReader<Preference>();
		reader.setDataSource(ds);
		reader.setSql("select * from preference");
		reader.setRowMapper(new PreferenceRowMapper());

		LOG.info("********* PREFERENCE READER CONFIGURED SUCCESSFULLY - {} *************", reader);
		return reader;
	}
}
