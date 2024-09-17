package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.domain.model.Rol;

import java.util.List;

public interface RolPersistencePort {

    void saveRol(Rol rol);

    List<Rol> getAllRol();

    void deleteRol(Long rolId);
}
