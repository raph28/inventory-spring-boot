package com.ennea.inventory.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.api.chunk.ItemReader;
import javax.batch.api.chunk.ItemWriter;

@Configuration
public class CSVBatchConfig {
//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory,
//                   StepBuilderFactory stepBuilderFactory,
//                   ItemReader<> itemReader,
//                   ItemProcessor<> itemProcessor,
//                   ItemWriter<> itemWriter
//                    ){
//        Step step=stepBuilderFactory.get('csv-file-upload')
//                .chunk(100)
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//           return jobBuilderFactory.get("CSV_Upload")
//                    .incrementer(new RunIdIncrementer())
//                    .start(step)
//                    .build();
//    }
}
