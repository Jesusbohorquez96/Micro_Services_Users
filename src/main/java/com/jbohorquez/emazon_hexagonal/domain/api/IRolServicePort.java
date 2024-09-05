package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.Rol;

import java.util.List;

public interface IRolServicePort {

    void saveRol(Rol rol);

    List<Rol> getAllRol();

    Rol getRolById(Long rolId);

    void updateRol(Rol rol);

    void deleteRol(Long rolId);
}
