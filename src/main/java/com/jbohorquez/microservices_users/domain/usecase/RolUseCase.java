package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.domain.api.IRolServicePort;
import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.microservices_users.infrastructure.exception.NameTooLongException;

import java.util.List;
import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

public class RolUseCase implements IRolServicePort {

    private final RolPersistencePort rolPersistencePort;

    public RolUseCase(RolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveRol(Rol rol) {
        if (rol.getName() == null || rol.getName().isEmpty()) {
            throw new IllegalArgumentException(NAME_REQUIRED);
        }
        if (rol.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException(NAME_LONG);
        }
        if (rol.getDescription() == null || rol.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException(DESCRIPTION_LONG);
        }
        rolPersistencePort.saveRol(rol);
    }

    @Override
    public List<Rol> getAllRol() {
        return rolPersistencePort.getAllRol();
    }

    @Override
    public void deleteRol(Long id) {
        rolPersistencePort.deleteRol(id);
    }
}
