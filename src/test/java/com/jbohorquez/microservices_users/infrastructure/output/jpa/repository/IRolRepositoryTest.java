package com.jbohorquez.microservices_users.infrastructure.output.jpa.repository;

import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IRolRepositoryTest {

    @Autowired
    private IRolRepository rolRepository;

    private RolEntity rolEntity;

    @BeforeEach
    void setUp() {
        rolEntity = new RolEntity();
        rolEntity.setName("Admin");
        rolEntity.setDescription("Administrator role");
    }

    @Test
    @Rollback(false)
    void testSaveRol() {
        RolEntity savedRol = rolRepository.save(rolEntity);
        assertNotNull(savedRol);
        assertEquals("Admin", savedRol.getName());
        assertEquals("Administrator role", savedRol.getDescription());
    }

    @Test
    void testFindById() {
        RolEntity savedRol = rolRepository.save(rolEntity);

        Optional<RolEntity> foundRol = rolRepository.findById(savedRol.getId());

        assertTrue(foundRol.isPresent());
        assertEquals("Admin", foundRol.get().getName());
        assertEquals("Administrator role", foundRol.get().getDescription());
    }

    @Test
    void testDeleteById() {
        RolEntity savedRol = rolRepository.save(rolEntity);

        rolRepository.deleteById(savedRol.getId());

        Optional<RolEntity> deletedRol = rolRepository.findById(savedRol.getId());
        assertFalse(deletedRol.isPresent());
    }

    @Test
    void testFindNonExistingRole() {
        Optional<RolEntity> foundRol = rolRepository.findRolByName("NonExistingRole");

        assertFalse(foundRol.isPresent());
    }
}