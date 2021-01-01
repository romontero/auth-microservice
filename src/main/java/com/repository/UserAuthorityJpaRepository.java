package com.repository;

import com.domain.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityJpaRepository extends JpaRepository<UserAuthorities,Long> {
}
