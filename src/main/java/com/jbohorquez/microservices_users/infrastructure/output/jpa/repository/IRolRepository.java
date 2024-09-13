package com.jbohorquez.microservices_users.infrastructure.output.jpa.repository;

import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findRolByName(String name);

}
