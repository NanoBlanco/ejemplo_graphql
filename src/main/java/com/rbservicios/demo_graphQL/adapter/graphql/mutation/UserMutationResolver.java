package com.rbservicios.demo_graphQL.adapter.graphql.mutation;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.UserGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import com.rbservicios.demo_graphQL.adapter.graphql.resolver.BaseGraphQLResolver;
import com.rbservicios.demo_graphQL.application.command.port.CreateUserCommand;
import com.rbservicios.demo_graphQL.domain.model.User;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class UserMutationResolver extends BaseGraphQLResolver {

    private final CreateUserCommand command;

    public UserMutationResolver(CreateUserCommand command) {
        this.command = command;
    }

    @MutationMapping
    public UserGql createUser(
            @Argument String email,
            @Argument String name
    ) {

        User user = command.execute(email, name);

        System.out.println("ID = " + user.getId());

        if (user == null) {
            throw new GraphQLException("User creation failed");
        }

        return UserGqlMapper.fromDomain(user);
    }
}
