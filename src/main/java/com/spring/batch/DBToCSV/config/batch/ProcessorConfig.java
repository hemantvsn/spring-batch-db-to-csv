package com.spring.batch.DBToCSV.config.batch;



import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.DBToCSV.model.LoggingPreferenceProcessor;
import com.spring.batch.DBToCSV.model.Preference;

@Configuration
public class ProcessorConfig {

	@Bean
    ItemProcessor<Preference, Preference> processor() {
        return new LoggingPreferenceProcessor();
    }
}
