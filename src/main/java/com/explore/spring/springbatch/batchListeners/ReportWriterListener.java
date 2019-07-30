package com.explore.spring.springbatch.batchListeners;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

@Component
public class ReportWriterListener implements ItemWriteListener<Object> {

    protected static final Logger LOG = Logger.getLogger(ReportWriterListener.class);

    @Override
    public void beforeWrite(List<?> list) {

    }

    @Override
    public void afterWrite(List<?> list) {
        LOG.error("After write");
    }

    @Override
    public void onWriteError(Exception e, List<?> list) {
        LOG.error("JOB ends with error");
    }
}
