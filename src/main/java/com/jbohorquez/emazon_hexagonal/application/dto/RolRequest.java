package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;

@Data
@Getter
@Setter
public class RolRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = DESCRIPTION_MAX_LENGTH, message = "The description must not exceed " + DESCRIPTION_MAX_LENGTH + " characters")
    private String description;
}
