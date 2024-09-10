package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Rol;
import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {User.class})
public interface UserResponseMapper {

    @Mapping(target = USER_ID_TARGET, source = ID)
    @Mapping(target = USER_NAME_TARGET, source = NAME)
    @Mapping(target = USER_LAST_NAME_TARGET, source = LAST_NAME)
    @Mapping(target = USER_IDENTITY_DOCUMENT_TARGET, source = IDENTITY_DOCUMENT)
    @Mapping(target = USER_PHONE_TARGET, source = PHONE)
    @Mapping(target = USER_BIRTHDATE_TARGET, source = BIRTHDATE)
    @Mapping(target = USER_EMAIL_TARGET, source = EMAIL)
    @Mapping(target = USER_PASSWORD_TARGET, source = PASSWORD_SOURCE)
    @Mapping(target = USER_ROL_TARGET, source = ROL)
    UserResponse toResponseList(User user);

    default String toRol(Rol rol) {
        if (rol == null) {
            return null;
        }
        return rol.getName();
    }
}