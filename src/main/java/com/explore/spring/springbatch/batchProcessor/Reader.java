package com.explore.spring.springbatch.batchProcessor;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

    private int count = 10;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count != 0) {
            count--;
            return count + " inside reader";
        } else {
            return null;
        }
    }
}
