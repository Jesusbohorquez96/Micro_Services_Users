package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.domain.model.Rol;

import java.util.List;

public interface IRolServicePort {

    void saveRol(Rol rol);

    List<Rol> getAllRol();

    void deleteRol(Long rolId);
}
