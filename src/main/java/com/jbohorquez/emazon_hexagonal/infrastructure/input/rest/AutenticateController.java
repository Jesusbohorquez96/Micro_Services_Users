package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.AuthenticationResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.RegisterRequest;
import com.jbohorquez.emazon_hexagonal.application.handler.IUsersHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticateController {

    private final IUsersHandler usersHandler;

    @Operation(summary = "Login", description = "Login to the application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(usersHandler.validateUser(authenticationRequest));
    }

    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(usersHandler.registerUser(registerRequest));
    }
}
