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

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setName(user.getName());
        return entity;
    }
}
