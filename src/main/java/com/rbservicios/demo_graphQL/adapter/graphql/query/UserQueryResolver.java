package com.rbservicios.demo_graphQL.adapter.graphql.query;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.UserGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import com.rbservicios.demo_graphQL.application.query.port.GetUserQuery;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserQueryResolver {

    private final GetUserQuery getUserQuery;

    public UserQueryResolver(GetUserQuery getUserQuery) {
        this.getUserQuery = getUserQuery;
    }

    @QueryMapping
    public UserGql userById(@Argument Long id) {
        return UserGqlMapper.fromDomain(
                getUserQuery.getById(id)
        );
    }

    @QueryMapping
    public List<UserGql> users() {
        return getUserQuery.getAll()
                .stream()
                .map(UserGqlMapper::fromDomain)
                .toList();
    }
}
