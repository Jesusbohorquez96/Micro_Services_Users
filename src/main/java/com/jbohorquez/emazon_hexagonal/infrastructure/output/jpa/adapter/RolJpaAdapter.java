package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.spi.RolPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@RequiredArgsConstructor
public class RolJpaAdapter implements RolPersistencePort {

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public void saveRol(Rol rol) {
        //validate that a brand with the same name does not exist
        if (rolRepository.findRolByName(rol.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        //validate your name is shorter than NAME_MAX_LENGTH characters
        if (rol.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException("Name is too long");
        }
        //validate if the description is shorter than DESCRIPTION MAX_LENGTH characters
        if (rol.getDescription() == null || rol.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException("Description is too long");
        }
        rolRepository.save(rolEntityMapper.toEntity(rol));
    }

    @Override
    public List<Rol> getAllRol() {
        List<RolEntity> rolEntityList = rolRepository.findAll();
        if (rolEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return rolEntityMapper.toRolList(rolEntityList);
    }

    @Override
    public Rol getRolById(Long rolId) {
        return rolEntityMapper.toRol(rolRepository.findById(rolId)
                .orElseThrow(AlreadyExistsException::new));
    }

    @Override
    public void updateRol(Rol rolId) {
        rolRepository.save(rolEntityMapper.toEntity(rolId));
    }

    @Override
    public void deleteRol(Long rolId) {
        rolRepository.deleteById(rolId);
    }
}
