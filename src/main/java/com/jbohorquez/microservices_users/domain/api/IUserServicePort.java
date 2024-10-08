package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserServicePort {

    void saveInUser(User user);

    List<User> getAllUser();

    void deleteUser(Long userId);

    Optional<User> findByEmail(String email);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    void registerUser(RegisterRequest registerRequest);
}

