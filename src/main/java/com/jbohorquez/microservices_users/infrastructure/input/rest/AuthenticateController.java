package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import com.jbohorquez.microservices_users.infrastructure.exception.InvalidCredentialsException;
import com.jbohorquez.microservices_users.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Collections;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@RestController
@RequestMapping(AUTH_API)
@RequiredArgsConstructor
public class AuthenticateController {

    private final IUsersHandler usersHandler;

    @Operation(summary = "Login", description = "Login to the application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })
    @PostMapping(LOGIN)
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = usersHandler.validateUser(authenticationRequest);
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EXIT.getMessage()));
        }
    }
}
