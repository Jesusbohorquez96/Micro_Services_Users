package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import com.jbohorquez.microservices_users.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@RestController
@RequestMapping(USERS_API)
@RequiredArgsConstructor
@Tag(name = TAG_USERS, description = API_USERS)
public class UsersRestController {

    private final IUsersHandler usersHandler;

    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User list returned successfully")
    })
    @GetMapping(ROOT)
//    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<List<UserResponse>> getFromUser() {
        return ResponseEntity.ok(usersHandler.getFromUser());
    }

    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_ADMIN)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(ADMIN);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }

    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_CUSTOMER)
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(CUSTOMER);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }
    
    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_AUX_BODEGA)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(AUX_BODEGA);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }

    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(USER_ID)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Void> deleteFromUser(@PathVariable Long userId) {
        usersHandler.deleteFromUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}