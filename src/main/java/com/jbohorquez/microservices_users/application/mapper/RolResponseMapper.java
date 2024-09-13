package com.jbohorquez.microservices_users.application.mapper;

import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Rol.class})
public interface RolResponseMapper {

    @Mapping(target = ROL_ID_TARGET, source = ID)
    @Mapping(target = ROL_NAME_TARGET, source = NAME)
    @Mapping(target = ROL_DESCRIPTION_TARGET, source = DESCRIPTION)
    RolResponse toResponse(Rol rol);
}
