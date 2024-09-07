package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.*;
import com.jbohorquez.emazon_hexagonal.application.handler.IUsersHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API for user management")
public class UsersRestController {

    private final IUsersHandler usersHandler;

    @Operation(summary = "Get paginated users", description = "Returns a paginated list of users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User list returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam(defaultValue = PAGE) int page,
            @RequestParam(defaultValue = SIZE) int size,
            @RequestParam(defaultValue = ASC) String sortDirection
    ) {
        Page<UserResponse> users = usersHandler.getUsers(page, size, sortDirection);
        return ResponseEntity.ok(users);
    }


    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User list returned successfully")
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getFromUser() {
        return ResponseEntity.ok(usersHandler.getFromUser());
    }

    @Operation(summary = "Get a user by ID", description = "Returns a specific user based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getFromUser(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(usersHandler.getFromUser(userId));
    }

    @Operation(summary = "Update a user", description = "Updates an existing user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Users actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateInUser(@Valid @RequestBody UserRequest userRequest) {
        usersHandler.updateInUser(userRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteFromUser(@PathVariable Long userId) {
        usersHandler.deleteFromUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
