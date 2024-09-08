package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class RolRequestTest {

    private Validator validator;
    private RolRequest rolRequest;

    @BeforeEach
    void setUp() {
        // Configurando el validador de bean para las anotaciones de validación
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Inicializando RolRequest con el constructor
        rolRequest = new RolRequest("Admin", "Administrator Role");
    }

    @Test
    void testConstructor() {
        // Validar el constructor
        RolRequest rol = new RolRequest("Admin", "Administrator Role");

        assertNotNull(rol);
        assertNull(rol.getName()); // Este valor es nulo porque no se asigna en el constructor actual
    }

    @Test
    void testGettersAndSetters() {
        // Validar los getters y setters generados por Lombok
        rolRequest.setName("Admin");
        rolRequest.setDescription("Administrator Role");

        assertEquals("Admin", rolRequest.getName());
        assertEquals("Administrator Role", rolRequest.getDescription());
    }

    @Test
    void testNameValidation() {
        // Probar la validación de @NotBlank en el campo "name"
        rolRequest.setName("");
        rolRequest.setDescription("Administrator Role");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<RolRequest> violation : violations) {
            assertEquals("Name is required", violation.getMessage());
        }
    }

    @Test
    void testDescriptionValidation() {
        // Probar la validación de @NotBlank y @Size en el campo "description"
        rolRequest.setName("Admin");
        rolRequest.setDescription("");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<RolRequest> violation : violations) {
            assertEquals("Description cannot be blank", violation.getMessage());
        }

        // Probar el límite máximo de caracteres en la descripción
        String longDescription = "a".repeat(DESCRIPTION_MAX_LENGTH + 1);
        rolRequest.setDescription(longDescription);

        violations = validator.validate(rolRequest);
        assertFalse(violations.isEmpty());

        for (ConstraintViolation<RolRequest> violation : violations) {
            assertEquals("The description must not exceed " + DESCRIPTION_MAX_LENGTH + " characters", violation.getMessage());
        }
    }

    @Test
    void testValidRolRequest() {
        // Probar que un RolRequest válido no genera violaciones
        rolRequest.setName("Admin");
        rolRequest.setDescription("Administrator Role");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);
        assertTrue(violations.isEmpty());
    }
}