package com.spring.batch.DBToCSV.config.batch;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.spring.batch.DBToCSV.model.Preference;

@Configuration
public class CSVWriterConfig {
	
	@Value("${csv.out.path}")
	private String csvOutPath;

	@Bean
	public FlatFileItemWriter<Preference> writer() {
		FlatFileItemWriter<Preference> writer = new FlatFileItemWriter<Preference>();
		writer.setResource(new FileSystemResource(csvOutPath + System.currentTimeMillis() + ".csv"));
		writer.setLineAggregator(new DelimitedLineAggregator<Preference>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Preference>() {
					{
						setNames(new String[] { "id", "preferenceKey", "preferenceType", "preferenceValue" });
					}
				});
			}
		});

		return writer;
	}
}
