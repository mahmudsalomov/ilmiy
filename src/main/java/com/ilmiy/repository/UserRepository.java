package com.ilmiy.repository;

import com.ilmiy.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
