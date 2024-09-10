package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class RolRequest {

    @NotBlank(message = NAME_REQUIRED )
    private String name;

    @NotBlank(message = DESCRIPTION_REQUIRED)
    @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_MAX_LENGTH_EXCEEDED)
    private String description;

    public RolRequest(String admin, String administratorRole) {
    }

    public RolRequest() {
    }
}
