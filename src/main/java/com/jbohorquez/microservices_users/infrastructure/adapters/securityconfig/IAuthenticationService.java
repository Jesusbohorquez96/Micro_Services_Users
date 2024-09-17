package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;

public interface IAuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void register(RegisterRequest registerRequest);
}
