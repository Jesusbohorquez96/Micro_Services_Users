package com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RolEntityMapper {

    RolEntity toEntity(Rol rol);

    Rol toRol(RolEntity rolEntity);

    List<Rol> toRolList(List<RolEntity> rolEntityList);
}
