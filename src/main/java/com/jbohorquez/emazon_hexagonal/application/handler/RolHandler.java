package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.RolRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.RolResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.RolRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.RolResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.IRolServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RolHandler implements IRolHandler {

    private final RolRequestMapper rolRequestMapper;
    private final RolResponseMapper rolResponseMapper;
    private final IRolServicePort rolServicePort;

    @Override
    public void saveInRol(RolRequest rolRequest) {
        Rol rol = rolRequestMapper.toRol(rolRequest);
        rolServicePort.saveRol(rol);
    }

    @Override
    public List<RolResponse> getAllRol() {
        List<Rol> rol = rolServicePort.getAllRol();
        return rol.stream()
                .map(rolResponseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RolResponse getRolById(Long rolId) {
        Rol rol = rolServicePort.getRolById(rolId);
        return rolResponseMapper.toResponse(rol);
    }

    @Override
    public void updateRol(RolRequest rolRequest) {
        Rol rol = rolRequestMapper.toRol(rolRequest);
        rolServicePort.updateRol(rol);
    }

    @Override
    public void deleteRol(Long rolId) {
        rolServicePort.deleteRol(rolId);
    }
}
