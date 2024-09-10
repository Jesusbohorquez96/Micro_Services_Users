package com.jbohorquez.emazon_hexagonal.infrastructure.configuration;

import com.jbohorquez.emazon_hexagonal.domain.spi.UserPersistencePort;
import com.jbohorquez.emazon_hexagonal.domain.usecase.UserUseCase;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationUser {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public UserUseCase userUseCase(UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        return new UserUseCase(userPersistencePort, passwordEncoder) {
        };
    }
}