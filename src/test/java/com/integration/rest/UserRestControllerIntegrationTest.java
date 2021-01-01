package com.integration.rest;

import com.AuthMicroserviceApp;
import com.domain.Authority;
import com.domain.User;
import com.integration.jpa.UserJpaIntegrationTest;
import com.rest.UserRestController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AuthMicroserviceApp.class)
@TestPropertySource("classpath:test.properties")
public class UserRestControllerIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestControllerIntegrationTest.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRestController userRestController;

    @Test
    public void createAndRetrieveUser(){
        User user = User.builder().username("aeiou").password("zjkml").build();
        Long id = userRestController.create(user);
        User gotUser = em.find(User.class,id);
        assertEquals(gotUser.getUsername(),user.getUsername());
        assertEquals(gotUser.getEnabled(),false);
        LOGGER.info("The Authority is: {}",gotUser.getUserAuthorities().get(0).getAuthority().name());
        assertEquals(gotUser.getUserAuthorities().get(0).getAuthority().name(), Authority.ROLE_USER);
    }
}
