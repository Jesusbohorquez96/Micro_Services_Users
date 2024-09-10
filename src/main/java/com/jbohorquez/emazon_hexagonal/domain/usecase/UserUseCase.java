package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.IUserServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

public abstract class UserUseCase implements IUserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public UserUseCase(UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;

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
}
