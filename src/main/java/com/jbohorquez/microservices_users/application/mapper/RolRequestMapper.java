package com.jbohorquez.microservices_users.application.mapper;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.SPRING;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RolRequestMapper {

    Rol toRol(RolRequest rolRequest);
}
