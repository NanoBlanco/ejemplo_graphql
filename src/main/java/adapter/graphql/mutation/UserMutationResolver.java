package adapter.graphql.mutation;

import adapter.graphql.context.UserContext;
import adapter.graphql.mapper.UserGqlMapper;
import adapter.graphql.model.UserGql;
import adapter.graphql.resolver.BaseGraphQLResolver;
import application.command.port.CreateUserCommand;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;

public class UserMutationResolver extends BaseGraphQLResolver {

    private final CreateUserCommand commad;

    public UserMutationResolver(CreateUserCommand commad) {
        this.commad = commad;
    }

    public UserGql createUser(
            @Argument String email,
            @Argument String name,
            DataFetchingEnvironment env
    ) {

        UserContext user = getUserContext(env);

        if (user == null) {
            throw new GraphQLException("Unauthorized");
        }

        return UserGqlMapper.fromDomain(commad.execute(email, name));
    }
}
