package com.explore.spring.springbatch.controller;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch/api/v1")
public class BatchController {

    protected static final Logger LOG = Logger.getLogger(BatchController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @GetMapping("/")
    public void tryBatch() {
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(processJob, jobParameters);
        } catch (Exception e) {
            LOG.error("Error : ", e);
        }
    }
}
