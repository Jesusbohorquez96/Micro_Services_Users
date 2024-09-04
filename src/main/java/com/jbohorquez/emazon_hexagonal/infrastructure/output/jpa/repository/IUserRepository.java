package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long userId);

    Optional<UserEntity> findByName(String name);

    void deleteById(Long userId);

    Optional<User> findByEmail(String email);

    boolean existsByIdentityDocument(Long identityDocument);
}
