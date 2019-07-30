package com.explore.spring.springbatch.batchProcessor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.explore.spring.springbatch.model.Employee;

@Component
public class ReportProcessor implements ItemProcessor<List<Employee>, String> {
    @Override
    public String process(List<Employee> groupItems) throws Exception {

        StringBuilder sb = new StringBuilder();

        for (Employee item : groupItems) {
            sb.append(item.getId());
            sb.append("\t");
            sb.append(item.getFirstName());
            sb.append("\t");
            sb.append(item.getLastName());
            sb.append("\t");
            sb.append(item.getDept());
            sb.append("\t");
            sb.append(item.getUserId());
            sb.append("\n");
        }

        return sb.toString();
    }
}
