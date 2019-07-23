package com.explore.spring.springbatch.batchProcessor;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String s : list) {
            System.out.println(s + " in writer");
        }
    }
}
