package com.explore.spring.springbatch.batchListeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.model.Employee;

@Component
public class ReaderListener implements ItemReadListener<Employee> {

    protected static final Logger LOG = Logger.getLogger(ReaderListener.class);

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Employee employee) {

    }

    @Override
    public void onReadError(Exception e) {
        LOG.error("error while reading : ", e);

    }
}
