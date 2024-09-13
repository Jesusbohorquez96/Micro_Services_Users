package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        UserEntity user = repository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generate(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        RolEntity rol = RolEntity.builder().id(registerRequest.getRol()).build();
        UserEntity user = UserEntity.builder().name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .identityDocument(registerRequest.getIdDocument())
                .phone(registerRequest.getPhone())
                .birthdate(registerRequest.getBirthdate())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .rol(rol)
                .build();

        repository.save(user);
        user = repository.findByEmail(user.getEmail()).orElseThrow();

        String jwtToken = jwtService.generate(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}