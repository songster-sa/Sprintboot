package com.explore.spring.springbatch.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{department}")
    public String createEmployeeByDepartment(@PathVariable(value = "department") String department) {
        try {
            long runtime = System.currentTimeMillis();
            JobParametersBuilder jobParameters = new JobParametersBuilder();
            jobParameters.addString("department", department);
            jobParameters.addLong("runtime", runtime);

            jobLauncher.run(processJob, jobParameters.toJobParameters());
            return "Job run at [" + new Date(runtime) + "]. Email will be sent out once done.";
        } catch (Exception e) {
            LOG.error("Error : ", e);
            return "could not run job" + e.getMessage();
        }
    }
}
