package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

        void saveUser(User user);

        List<User> getAllUser();

        void deleteUser(Long userId);

        Page<User> findAllUser(Pageable pageable);

        Page<User> getUsers(PageRequest pageRequest);

        Optional<User> findByEmail(String email);

}

