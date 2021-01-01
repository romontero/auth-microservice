package com.rest;

import com.domain.User;
import com.service.iface.UserService;
import com.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.util.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = BASE_URL_API)
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = AUTH_ADMIN+URL_CREATE_USER,consumes = {APPLICATION_JSON_VALUE})
    public Long create(@RequestBody User user){
        return userService.create(user);
    }

}
