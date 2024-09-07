package com.jbohorquez.emazon_hexagonal.infrastructure.configuration;

import com.jbohorquez.emazon_hexagonal.domain.api.IRolServicePort;
import com.jbohorquez.emazon_hexagonal.domain.spi.RolPersistencePort;
import com.jbohorquez.emazon_hexagonal.domain.usecase.RolUseCase;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter.RolJpaAdapter;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationRol {

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Bean
    public RolPersistencePort rolPersistencePort() {
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort() {
        return new RolUseCase(rolPersistencePort()) {
        };
    }
}
