package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.domain.api.IUserServicePort;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NameTooLongException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

public abstract class UserUseCase implements IUserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final IAuthenticationService authenticationService;

    public UserUseCase(UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, IAuthenticationService authenticationService) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

    @Override
    public void saveInUser(User user) {
        Optional<User> userOptional = userPersistencePort.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsException();
        }
        if (user.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException(NAME_LONG);
        }
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userPersistencePort.getAllUser();
    }

    @Override
    public User getUserById(Long userId) {
        return userPersistencePort.getUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userPersistencePort.deleteUser(userId);
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
