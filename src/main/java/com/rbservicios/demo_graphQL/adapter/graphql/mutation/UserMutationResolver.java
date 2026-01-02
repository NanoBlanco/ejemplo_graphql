package com.rbservicios.demo_graphQL.adapter.graphql.mutation;

import com.rbservicios.demo_graphQL.adapter.graphql.mapper.UserGqlMapper;
import com.rbservicios.demo_graphQL.adapter.graphql.model.UserGql;
import com.rbservicios.demo_graphQL.adapter.graphql.resolver.BaseGraphQLResolver;
import com.rbservicios.demo_graphQL.application.command.port.CreateUserCommand;
import com.rbservicios.demo_graphQL.application.command.port.DeleteUserCommand;
import com.rbservicios.demo_graphQL.application.command.port.UpdateUserCommand;
import com.rbservicios.demo_graphQL.domain.model.User;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserMutationResolver extends BaseGraphQLResolver {

    private final CreateUserCommand command;
    private final UpdateUserCommand updateUserCommand;
    private final DeleteUserCommand deleteUserCommand;

    public UserMutationResolver(
            CreateUserCommand command,
            UpdateUserCommand updateUserCommand,
            DeleteUserCommand deleteUserCommand
    ) {
        this.command = command;
        this.updateUserCommand = updateUserCommand;
        this.deleteUserCommand = deleteUserCommand;
    }

    @MutationMapping
    public UserGql createUser(
            @Argument String email,
            @Argument String name
    ) {
        User user = command.execute(email, name);

        if (user == null) {
            throw new GraphQLException("User creation failed");
        }

        return UserGqlMapper.fromDomain(user);
    }

    @MutationMapping
    public UserGql updateUser(
            @Argument Long id,
            @Argument String email,
            @Argument String name
    ) {
        return UserGqlMapper.fromDomain(
                updateUserCommand.execute(id, email, name)
        );
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        deleteUserCommand.execute(id);
        return true;
    }
}
