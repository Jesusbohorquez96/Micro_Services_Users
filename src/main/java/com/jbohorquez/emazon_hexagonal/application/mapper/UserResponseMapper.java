package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {User.class})
public interface UserResponseMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userName", source = "name")
    @Mapping(target = "userLastName", source = "lastName")
    @Mapping(target = "userIdentityDocument", source = "identityDocument")
    @Mapping(target = "userPhone", source = "phone")
    @Mapping(target = "userBirthdate", source = "birthdate")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "userPassword", source = "password")
    @Mapping(target = "userRol", source = "rol")
    UserResponse toResponseList(User user);

    default String toRol(Rol rol) {
        if (rol == null) {
            return null;
        }
        return rol.getName();
    }
}