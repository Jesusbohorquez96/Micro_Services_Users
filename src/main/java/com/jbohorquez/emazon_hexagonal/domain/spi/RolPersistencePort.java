package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.Rol;

import java.util.List;

public interface RolPersistencePort {

    void saveRol(Rol rol);

    List<Rol> getAllRol();

    Rol getRolById(Long rolId);

    void updateRol(Rol rolId);

    void deleteRol(Long rolId);
}
