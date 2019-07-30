package com.explore.spring.springbatch.batchProcessor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.model.Employee;

@Component
public class Processor implements ItemProcessor<Employee, Employee> {

    protected static final Logger LOG = Logger.getLogger(Processor.class);

    @Override
    public Employee process(Employee employee) throws Exception {

        // try to create account and set user id
        try {
            if (employee.getId() % 13 == 0) {
                throw new Exception("unable to create account for employee with id " + employee.getId());
            }
            employee.setUserId("userId" + employee.getId());
        } catch (Exception e) {
            // otherwise the job halts!
            LOG.error("error ", e);
        }
        return employee;
    }
}
