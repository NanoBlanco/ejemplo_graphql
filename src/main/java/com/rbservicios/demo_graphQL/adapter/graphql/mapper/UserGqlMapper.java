package com.rbservicios.demo_graphQL.adapter.graphql.mapper;

import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import com.rbservicios.demo_graphQL.domain.model.User;

public class UserGqlMapper {

    public static UserGql fromDomain(User user) {
        return new UserGql(
                user.getId(),
                user.getEmail(),
                user.getName()
        );

    }
}
