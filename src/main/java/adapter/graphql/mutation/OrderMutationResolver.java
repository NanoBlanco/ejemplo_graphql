package adapter.graphql.mutation;

import adapter.graphql.context.UserContext;
import adapter.graphql.mapper.OrderGqlMapper;
import adapter.graphql.model.OrderGql;
import adapter.graphql.resolver.BaseGraphQLResolver;
import application.command.port.CreateOrderCommand;
import graphql.GraphQLException;
import graphql.GraphqlErrorBuilder;
import org.springframework.graphql.execution.ErrorType;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMutationResolver extends BaseGraphQLResolver {

    private final CreateOrderCommand command;

    public OrderMutationResolver(CreateOrderCommand command) {
        this.command = command;
    }

    @MutationMapping
    public OrderGql createOrder(
            @Argument List<Long> productIds,
            DataFetchingEnvironment env) throws Throwable {

        UserContext user = getUserContext(env);

        if (user == null) {
            throw new GraphQLException("Unauthorized");
        }

        if (!user.hasRole("USER")) {
            throw (Throwable) GraphqlErrorBuilder.newError()
                    .message("Forbidden")
                    .errorType(ErrorType.FORBIDDEN)
                    .build();
        }

        return OrderGqlMapper.fromDomain(command.execute(user.getUserId(), productIds));
    }

}
