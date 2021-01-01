package com.integration;

import com.AuthMicroserviceApp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = AuthMicroserviceApp.class)
@TestPropertySource("classpath:test.properties")
public class SpringBootAppIntegrationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoad(){
        assertNotNull(applicationContext);
    }
}
