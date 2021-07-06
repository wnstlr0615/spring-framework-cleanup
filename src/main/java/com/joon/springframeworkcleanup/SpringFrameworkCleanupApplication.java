package com.joon.springframeworkcleanup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@PropertySource("classpath:/app.properties")
//@EnableAsync
public class SpringFrameworkCleanupApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkCleanupApplication.class, args);
    }

}
