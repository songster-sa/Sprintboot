package com.explore.spring.springbatch.batchListeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener extends JobExecutionListenerSupport {

    protected static final Logger LOG = Logger.getLogger(JobCompletionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {

        LOG.info("BATCH JOB COMPLETED with status " + jobExecution.getStatus());

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }
}
