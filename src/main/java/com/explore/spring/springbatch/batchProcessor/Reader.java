package com.explore.spring.springbatch.batchProcessor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.model.Employee;

@StepScope
@Component
public class Reader implements ItemReader<Employee> {

    private int count = 20;
    private String dept;

    @Override
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count != 0) {
            count--;
            return new Employee(count, "first" + count, "last" + count, getDept());
        } else {
            return null;
        }
    }

    @Value("#{jobParameters['department']}")
    public void setRuntime(final String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

}
