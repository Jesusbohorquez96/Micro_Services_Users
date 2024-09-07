package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);

}
