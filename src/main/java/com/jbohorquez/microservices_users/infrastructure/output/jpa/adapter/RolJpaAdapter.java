package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class RolJpaAdapter implements RolPersistencePort {

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public void saveRol(Rol rol) {
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
