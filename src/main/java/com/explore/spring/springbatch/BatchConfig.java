package com.explore.spring.springbatch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.explore.spring.springbatch.batchListeners.ItemCountListener;
import com.explore.spring.springbatch.batchListeners.JobCompletionListener;
import com.explore.spring.springbatch.batchListeners.ProcessListener;
import com.explore.spring.springbatch.batchListeners.ReaderListener;
import com.explore.spring.springbatch.batchListeners.ReportWriterListener;
import com.explore.spring.springbatch.batchProcessor.Processor;
import com.explore.spring.springbatch.batchProcessor.Reader;
import com.explore.spring.springbatch.batchProcessor.ReportProcessor;
import com.explore.spring.springbatch.batchProcessor.ReportReader;
import com.explore.spring.springbatch.batchProcessor.ReportWriter;
import com.explore.spring.springbatch.batchProcessor.Writer;
import com.explore.spring.springbatch.model.Employee;

@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobCompletionListener completionListener;
    @Autowired
    private Reader reader;
    @Autowired
    private ReaderListener readerListener;
    @Autowired
    private Processor processor;
    @Autowired
    private ProcessListener processListener;
    @Autowired
    private Writer writer;
    @Autowired
    private ReportReader reportReader;
    @Autowired
    private ReportProcessor reportProcessor;
    @Autowired
    private ReportWriter reportWriter;
    @Autowired
    private ReportWriterListener reportWriterListener;
    @Autowired
    private ItemCountListener chunkListener;

   /* @Bean
    @Primary
    @ConfigurationProperties(prefix="batch.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }*/

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(completionListener)
                .start(create()).next(report()).build();
    }

    @Bean
    public Step create() {
        return stepBuilderFactory.get("create").<Employee, Employee>chunk(10)
                .reader(reader)
                .listener(readerListener)
                .processor(processor)
                //.listener(processListener)
                .writer(writer)
                .listener(promotionListener())
                .listener(chunkListener).build();
    }

    @Bean
    public Step report() {
        return stepBuilderFactory.get("report").<List<Employee>, String>chunk(1)
                .reader(reportReader)
                .processor(reportProcessor)
                .writer(reportWriter)
                .listener(reportWriterListener)
                .build();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener() {
        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[] { EmployeeConstants.group_report });
        listener.setStrict(true);
        return listener;
    }
}
