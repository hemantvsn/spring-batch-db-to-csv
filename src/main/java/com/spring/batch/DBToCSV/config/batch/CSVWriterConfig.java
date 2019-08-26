package com.spring.batch.DBToCSV.config.batch;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.spring.batch.DBToCSV.model.Preference;

@Configuration
public class CSVWriterConfig {

	public FlatFileItemWriter<Preference> writer() {
		FlatFileItemWriter<Preference> writer = new FlatFileItemWriter<Preference>();
		writer.setResource(new ClassPathResource("preferences.csv"));
		writer.setLineAggregator(new DelimitedLineAggregator<Preference>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Preference>() {
					{
						setNames(new String[] { "id", "preferenceKey", "preferenceType", "prefereenceValue" });
					}
				});
			}
		});

		return writer;
	}
}
