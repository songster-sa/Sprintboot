package com.explore.spring.springbatch.batchProcessor;

import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.EmployeeConstants;
import com.explore.spring.springbatch.model.Employee;

@Component
@StepScope
public class ReportReader implements ItemReader<List<Employee>> {

    private List<List<Employee>> reportItems;
    private int counter;

    @Override
    public List<Employee> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        for (int i = 0; i < counter; i++) {
            return getReportItems().get(i); // TODO SA keeps returning the 0th index always
        }

        return null;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        if (this.reportItems == null) {
            JobExecution jobExecution = stepExecution.getJobExecution();
            ExecutionContext jobContext = jobExecution.getExecutionContext();
            this.reportItems = (List<List<Employee>>) jobContext.get(EmployeeConstants.group_report);
            this.counter = this.reportItems.size();
        }
    }

    public List<List<Employee>> getReportItems() {
        return reportItems;
    }
}
