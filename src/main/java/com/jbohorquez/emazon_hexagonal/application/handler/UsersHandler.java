package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.*;
import com.jbohorquez.emazon_hexagonal.application.mapper.UserRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.UserResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.IUserServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.infrastructure.adapters.securityconfig.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersHandler implements IUsersHandler {

    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;
    private final IAuthenticationService authenticationService;

    @Override
    public void saveInUser(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        userServicePort.saveInUser(user);
    }

    @Override
    public List<UserResponse> getFromUser() {
        List<User> users = userServicePort.getAllUser();
        return users.stream()
                .map(userResponseMapper::toResponseList)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getFromUser(Long userId) {
        User user = userServicePort.getUserById(userId);
        return userResponseMapper.toResponseList(user);
    }

    @Override
    public void updateInUser(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        userServicePort.updateUser(user);
    }

    @Override
    public void deleteFromUser(Long userId) {
        userServicePort.deleteUser(userId);
    }

    @Override
    public AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

    @Override
    public AuthenticationResponse registerUser(RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }
}
