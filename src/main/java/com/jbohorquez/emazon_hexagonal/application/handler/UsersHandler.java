package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.UserRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.UserResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.IUserServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESC;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersHandler implements IUsersHandler {

    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getUsers(int page, int size, String sortDirection) {
        Sort.Direction direction = DESC.equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return userServicePort.getUsers(page, size, direction)
                .map(userResponseMapper::toResponseList);
    }

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
    public boolean validateUser(String email, String password) {
        Optional<User> userOpt = userServicePort.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
