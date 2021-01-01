package com.repository;

import com.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User,Long> {
}
