package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.jwtentrypoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(JSON);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(ERROR_JWT);
    }
}
