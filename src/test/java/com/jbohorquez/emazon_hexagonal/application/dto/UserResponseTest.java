package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        UserResponse userResponse = new UserResponse();
        Long userId = 1L;
        String userName = "John";
        String userLastName = "Doe";
        Long userIdentityDocument = 123456789L;
        String userPhone = "+1234567890";
        LocalDate userBirthdate = LocalDate.of(1990, 1, 1);
        String userEmail = "john@example.com";
        String userPassword = "password123";
        String userRol = "ADMIN";

        // Act
        userResponse.setUserId(userId);
        userResponse.setUserName(userName);
        userResponse.setUserLastName(userLastName);
        userResponse.setUserIdentityDocument(userIdentityDocument);
        userResponse.setUserPhone(userPhone);
        userResponse.setUserBirthdate(userBirthdate);
        userResponse.setUserEmail(userEmail);
        userResponse.setUserPassword(userPassword);
        userResponse.setUserRol(userRol);

        // Assert
        assertEquals(userId, userResponse.getUserId());
        assertEquals(userName, userResponse.getUserName());
        assertEquals(userLastName, userResponse.getUserLastName());
        assertEquals(userIdentityDocument, userResponse.getUserIdentityDocument());
        assertEquals(userPhone, userResponse.getUserPhone());
        assertEquals(userBirthdate, userResponse.getUserBirthdate());
        assertEquals(userEmail, userResponse.getUserEmail());
        assertEquals(userPassword, userResponse.getUserPassword());
        assertEquals(userRol, userResponse.getUserRol());
    }
}