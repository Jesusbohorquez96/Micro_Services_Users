package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IUserServicePort {

    void saveInUser(User user);

    List<User> getAllUser();

    User getUserById(Long userId);

    void updateUser(User user);

    void deleteUser(Long userId);

    Page<User> getUsers(int pageNumber, int pageSize, Sort.Direction sortDirection);

    Optional<User> findByEmail(String email);
}

