package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;

import java.util.List;

public interface IRolHandler {

    void saveInRol(RolRequest rolRequest);

    List<RolResponse> getAllRol();

    void deleteRol(Long rolId);
}


