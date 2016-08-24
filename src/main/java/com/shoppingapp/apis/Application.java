package com.shoppingapp.apis;

/**
 * Created by eri_k on 2/21/2016.
 */

import jdk.nashorn.internal.runtime.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.util.Arrays;

@ComponentScan(basePackages = {"com.shoppingapp.apis"})

@Configuration
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            //System.out.println(beanName);
            if (beanName.contains("Controller")) System.out.println(beanName);
        }
   /* Read more :http://mrbool.com/rest-server-with-spring-data-spring-boot-and-postgresql/34023#ixzz40oAA4qI3*/

    }
}





