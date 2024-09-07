package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUsersHandler {

    Page<UserResponse> getUsers(int page, int size, String sortDirection);

    void saveInUser(UserRequest userRequest);

    List<UserResponse> getFromUser();

    UserResponse getFromUser(Long userId);

    void updateInUser(UserRequest userRequest);

    void deleteFromUser(Long userId);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    AuthenticationResponse registerUser(RegisterRequest registerRequest);

}
