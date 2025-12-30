package com.rbservicios.demo_graphQL.infraestructure.persistence.mapper;

import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity user) {

        return new User(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
