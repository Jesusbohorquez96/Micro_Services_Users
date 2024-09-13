package com.jbohorquez.microservices_users.application.mapper;

import com.jbohorquez.microservices_users.application.dto.UserRequest;
import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(target = ROL, source = ROL, qualifiedByName = TO_ROL)
    User toUser(UserRequest userRequest);

    @Named(TO_ROL)
    default Rol toRol(Long rol) {
        Rol rol1 = new Rol();
        rol1.setId(rol);
        return rol1;
    }
}