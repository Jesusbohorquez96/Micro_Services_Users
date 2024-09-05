package com.jbohorquez.emazon_hexagonal.infrastructure.adapters.securityconfig;

import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.RegisterRequest;
import com.jbohorquez.emazon_hexagonal.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.AUX;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserEntity user = repository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generate(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        UserEntity user = UserEntity.builder().name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .id(registerRequest.getId())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(AUX).build();

        repository.save(user);

        return AuthenticationResponse.builder().token(jwtService.getToken(user)).build();
    }




}