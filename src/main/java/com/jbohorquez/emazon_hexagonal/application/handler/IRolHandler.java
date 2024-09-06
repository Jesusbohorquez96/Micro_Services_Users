package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.RolRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.RolResponse;

import java.util.List;

public interface IRolHandler {

    void saveInRol(RolRequest rolRequest);

    List<RolResponse> getAllRol();

    RolResponse getRolById(Long rolId);

    void updateRol(RolRequest rolRequest);

    void deleteRol(Long rolId);
}


