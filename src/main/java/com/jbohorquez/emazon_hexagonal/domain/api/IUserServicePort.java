package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserServicePort {

    void saveInUser(User user);

    List<User> getAllUser();

    User getUserById(Long userId);

    void updateUser(User user);

    void deleteUser(Long userId);

    Optional<User> findByEmail(String email);
}

