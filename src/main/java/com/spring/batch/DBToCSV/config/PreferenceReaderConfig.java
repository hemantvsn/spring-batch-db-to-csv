package com.spring.batch.DBToCSV.config;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.DBToCSV.model.Preference;
import com.spring.batch.DBToCSV.model.PreferenceRowMapper;

@Configuration
public class PreferenceReaderConfig {
	
	@Autowired
	private DataSource ds;

	@Bean
	 public JdbcCursorItemReader<Preference> reader(){
	  JdbcCursorItemReader<Preference> reader = new JdbcCursorItemReader<Preference>();
	  reader.setDataSource(ds);
	  reader.setSql("select * from preference");
	  reader.setRowMapper(new PreferenceRowMapper());
	  
	  return reader;
	 }
}
