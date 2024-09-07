package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(target = "rol", source = "rol", qualifiedByName = "toRol")
    User toUser(UserRequest userRequest);

    @Named("toRol")
    default Rol toRol(Long rol) {
        Rol rol1 = new Rol();
        rol1.setId(rol);
        return rol1;
    }
}