package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.constants.ValidationConstants;
import com.jbohorquez.emazon_hexagonal.domain.api.IUserServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public void saveUser(User user) {
        final String AUX = ValidationConstants.AUX;
        final String CUSTOMER = ValidationConstants.CUSTOMER;
        System.out.println("UserUseCase.saveUser"+ user.getRole());
        if (!AUX.equals(user.getRole()) && !CUSTOMER.equals(user.getRole())) {
            throw new IllegalArgumentException("Invalid role. Must be either 'aux_bodega' or 'customer'.");
        }
        System.out.println("UserUseCase.saveUser" + user.getRole());
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
        // Encriptar la contraseña antes de actualizar
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userPersistencePort.deleteUser(userId);
    }

    @Override
    public Page<User> getUsers(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, NAME);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userPersistencePort.findAllUser(pageable);
    }
}
