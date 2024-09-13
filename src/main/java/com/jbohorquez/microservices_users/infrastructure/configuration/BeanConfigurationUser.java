package com.jbohorquez.microservices_users.infrastructure.configuration;

import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.domain.usecase.UserUseCase;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
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
    public UserUseCase userUseCase(UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder, IAuthenticationService authenticationService) {
        return new UserUseCase(userPersistencePort, passwordEncoder, authenticationService) {
        };
    }
}