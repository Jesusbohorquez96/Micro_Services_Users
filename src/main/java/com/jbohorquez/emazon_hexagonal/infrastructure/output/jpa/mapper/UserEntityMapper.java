package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(target = "rol", source = "rol", qualifiedByName = "stringToRolEntity")
    UserEntity toEntity(User user);

    @Mapping(target = "rol", source = "rol", qualifiedByName = "rolEntityToString")
    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);

    @Named("stringToRolEntity")
    default RolEntity stringToRolEntity(String rol) {
        if (rol == null) {
            return null;
        }
        RolEntity rolEntity = new RolEntity();
        rolEntity.setName(rol);
        return rolEntity;
    }

    @Named("rolEntityToString")
    default String rolEntityToString(RolEntity rolEntity) {
        if (rolEntity == null) {
            return null;
        }
        return rolEntity.getName();
    }
}
