package com.explore.spring.springbatch.batchProcessor;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ReportWriter implements ItemWriter<String> {

    private long runtime;
    protected static final Logger LOG = Logger.getLogger(ReportWriter.class);

    @Override
    public void write(List<? extends String> list) throws Exception {

        // create email request and send

        LOG.info("subject : Report for [create employee] job run at : " + new Date(getRuntime()));

        for (String item : list) {
            LOG.info("content : " + item);
        }

        LOG.info("Email sent.");

    }

    @Value("#{jobParameters['runtime']}")
    public void setRuntime(final long runtime) {
        this.runtime = runtime;
    }

    public long getRuntime() {
        return runtime;
    }
}
