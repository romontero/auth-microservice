package com.security;

import com.domain.Authority;
import com.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.util.Constants.AUTH_ADMIN;
import static com.util.Constants.BASE_URL_API;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthMicroserviceAppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE_DEFAULT = "/login";
    private static final String USERNAME_PARAMETER = "username";
    private static final String PASS_PARAMETER = "password";
    private static final String SESSION_ID = "JSESSIONID";
    private static final String ROOTS_URL = "/**";

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM user_authorities INNER JOIN users ON user_authorities.user_id = users.id WHERE users.username=?")
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers(BASE_URL_API+AUTH_ADMIN+ROOTS_URL).hasRole(Authority.ROLE_ADMIN.getShortForm())
                .and()
                .formLogin()
                .usernameParameter(USERNAME_PARAMETER)
                .passwordParameter(PASS_PARAMETER)
                .permitAll()
                .and()
                .csrf()
                .disable()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
