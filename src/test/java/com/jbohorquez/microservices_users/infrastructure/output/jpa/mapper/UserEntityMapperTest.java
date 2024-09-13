package com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper;

import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityMapperTest {

    private UserEntityMapper userEntityMapper;

    @BeforeEach
    public void setUp() {
        userEntityMapper = Mappers.getMapper(UserEntityMapper.class);
    }

    @Test
    public void testToEntity() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        UserEntity userEntity = userEntityMapper.toEntity(user);

        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getId()).isEqualTo(1L);
        assertThat(userEntity.getName()).isEqualTo("John");
        assertThat(userEntity.getLastName()).isEqualTo("Doe");
        assertThat(userEntity.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testToUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Jane");
        userEntity.setLastName("Smith");
        userEntity.setEmail("jane.smith@example.com");

        User user = userEntityMapper.toUser(userEntity);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Jane");
        assertThat(user.getLastName()).isEqualTo("Smith");
        assertThat(user.getEmail()).isEqualTo("jane.smith@example.com");
    }

    @Test
    public void testToUserList() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setName("User1");
        userEntity1.setLastName("LastName1");
        userEntity1.setEmail("user1@example.com");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        userEntity2.setName("User2");
    }
}