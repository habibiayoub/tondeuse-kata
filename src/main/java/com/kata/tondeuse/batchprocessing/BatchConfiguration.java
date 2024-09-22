package com.kata.tondeuse.batchprocessing;

import com.kata.tondeuse.entity.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<Object> reader() {
        return new FlatFileItemReaderBuilder<Object>()
                .name("tondeuseItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .lineMapper(new CustomLineMapper())
                .build();
    }

    @Bean
    public TondeuseItemProcessor processor() {
        return new TondeuseItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Tondeuse> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Tondeuse>()
                .sql("INSERT INTO tondeuse (x, y, direction) VALUES (:x, :y, :directionAsChar)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importTondeuseJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importTondeuseJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<Object> reader, TondeuseItemProcessor processor, JdbcBatchItemWriter<Tondeuse> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Object, Tondeuse> chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
