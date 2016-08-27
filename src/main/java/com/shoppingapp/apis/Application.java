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
   /* Read more :http://mrbool.com/rest-server-with-spring-data-spring-boot-and-postgresql/34023#ixzz40oAA4qI3*/

    }
}





