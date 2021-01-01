package com.integration.jpa;

import com.AuthMicroserviceApp;
import com.domain.User;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = AuthMicroserviceApp.class)
@TestPropertySource("classpath:test.properties")
public class UserJpaIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJpaIntegrationTest.class);

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "jadams";

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PasswordEncoder encoder;


    @Test
    @Transactional
    public void findUser(){
        User user = em.find(User.class,USER_ID);
        assertEquals(user.getUsername(),USERNAME);
    }

    @Test
    @Transactional
    public void simpleJpaOperation(){
        User user = User.builder().username("awqlk").password("poijn").enabled(false).build();
        em.persist(user);
        User gotUser = em.find(User.class,user.getId());
        assertEquals(gotUser.getUsername(),user.getUsername());
        assertEquals(gotUser.getPassword(),user.getPassword());
        assertEquals(gotUser.getEnabled(),false);
    }

}
