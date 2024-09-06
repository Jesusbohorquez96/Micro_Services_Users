package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RolEntityMapper {

    RolEntity toEntity(Rol rol);

    Rol toRol(RolEntity rolEntity);

    List<Rol> toRolList(List<RolEntity> rolEntityList);
}
