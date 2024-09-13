package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.*;

import java.util.List;

public interface IUsersHandler {

    void saveInUser(UserRequest userRequest);

    List<UserResponse> getFromUser();

    UserResponse getFromUser(Long userId);

    void updateInUser(UserRequest userRequest);

    void deleteFromUser(Long userId);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    AuthenticationResponse registerUser(RegisterRequest registerRequest);

}
