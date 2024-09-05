package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.IRolServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.spi.RolPersistencePort;
import java.util.List;

public class RolUseCase implements IRolServicePort {


    private final RolPersistencePort rolPersistencePort;

    public RolUseCase(RolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveRol(Rol rol) {
        rolPersistencePort.saveRol(rol);
    }

    @Override
    public List<Rol> getAllRol() {
        return rolPersistencePort.getAllRol();
    }

    @Override
    public Rol getRolById(Long rolId) {
        return rolPersistencePort.getRolById(rolId);
    }

    @Override
    public void updateRol(Rol rol) {
        rolPersistencePort.updateRol(rol);
    }

    @Override
    public void deleteRol(Long id) {
        rolPersistencePort.deleteRol(id);
    }
}
