package com;

import com.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuthMicroserviceApp extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthMicroserviceApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthMicroserviceApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
