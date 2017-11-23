package com.makemytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * Bootstrap class for the app.
 */
@SpringBootApplication(scanBasePackages = { "com.makemytrip.*" })
@EnableAsync
@EnableScheduling
public class App {

    public static void main(String[] args) {
        System.out.println("App started");

        SpringApplication.run(App.class, args);
    }
}
