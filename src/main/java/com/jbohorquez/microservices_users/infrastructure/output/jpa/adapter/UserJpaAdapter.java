package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
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
