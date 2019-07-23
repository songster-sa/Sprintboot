/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.explore.spring.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * The main entry point for SpringBoot, this is also the main configuration file
 * for any SpringBoot properties. @EnableAutoConfiguration should only ever be on this class,
 * other classes may use @Configuration.
 */

@ComponentScan
@ImportResource("classpath:springConfig.xml")
@PropertySource("classpath:springConfig.properties")
@EnableBatchProcessing
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}
