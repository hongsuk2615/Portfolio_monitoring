package com.portfolio.monitoring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.monitoring.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String name);
}
