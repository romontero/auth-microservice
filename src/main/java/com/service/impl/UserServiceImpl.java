package com.service.impl;

import com.domain.Authority;
import com.domain.User;
import com.domain.UserAuthorities;
import com.repository.UserAuthorityJpaRepository;
import com.repository.UserJpaRepository;
import com.service.iface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserJpaRepository userJpaRepository;

    private UserAuthorityJpaRepository userAuthorityJpaRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl(UserJpaRepository userJpaRepository, UserAuthorityJpaRepository userAuthorityJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userAuthorityJpaRepository = userAuthorityJpaRepository;
    }

    @Override
    public Long create(User user) {
        user = userJpaRepository.saveAndFlush(User.builder().username(user.getUsername()).password(user.getPassword()).enabled(false).build());
        return addUserAuthority(user);
    }

    public Long addUserAuthority(User user) {
        return userAuthorityJpaRepository.save(UserAuthorities.builder().authority(Authority.ROLE_USER).user(user).build()).getUser().getId();
    }
}
