package com.interpark.hermes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class HermesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HermesApplication.class, args);
    }
}