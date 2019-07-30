package com.explore.spring.springbatch.batchListeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.model.Employee;

/**
 * this is not linked to the job as of now... because i am not able to get stepExecution reference here
 */
@Component
public class ProcessListener implements ItemProcessListener<Employee, Object> {

    private StepExecution stepExecution;
    protected static final Logger LOG = Logger.getLogger(ProcessListener.class);

    @Override
    public void beforeProcess(Employee item) {

    }

    @Override
    public void afterProcess(Employee item, Object result) {

    }

    @Override
    public void onProcessError(Employee item, Exception e) {

        LOG.error("error ", e);
        /*ExecutionContext stepContext = stepExecution.getExecutionContext();

        Object errorReport = stepContext.get(EmployeeConstants.error_report);

        if (errorReport == null) {
            errorReport = new ArrayList<Employee>();
        }
        ((List<Employee>) errorReport).add(item);

        stepContext.put(EmployeeConstants.error_report, errorReport);*/
    }

    @BeforeStep
    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
