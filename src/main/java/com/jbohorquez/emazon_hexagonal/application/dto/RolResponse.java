package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolResponse {

    private Long rolId;
    private String rolName;
    private String rolDescription;

    public RolResponse(long l, String admin, String adminRole) {
    }

}
