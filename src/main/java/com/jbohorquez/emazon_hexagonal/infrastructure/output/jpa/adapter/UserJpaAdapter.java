package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        //validate that a brand with the same name does not exist
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        //validate your name is shorter than NAME_MAX_LENGTH characters
        if (user.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException("Name is too long");
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUserById(Long userId) {
        return userEntityMapper.toUser(userRepository.findById(userId)
                .orElseThrow(AlreadyExistsException::new));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Page<User> getUsers(PageRequest pageRequest) {
        Page<UserEntity> userEntityPage = userRepository.findAll(pageRequest);
        if (userEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityPage.map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::toUser);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable).map(userEntityMapper::toUser);
    }
}
