package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.RolResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Rol.class})
public interface RolResponseMapper {

    @Mapping(target = "rolId", source = "id")
    @Mapping(target = "rolName", source = "name")
    @Mapping(target = "rolDescription", source = "description")
    RolResponse toResponse(Rol rol);
}
