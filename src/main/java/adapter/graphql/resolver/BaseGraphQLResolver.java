package adapter.graphql.resolver;

import adapter.graphql.context.UserContext;
import graphql.schema.DataFetchingEnvironment;

public abstract class BaseGraphQLResolver {

    protected UserContext getUserContext(DataFetchingEnvironment env) {
        return env.getGraphQlContext().get("userContext");
    }
}
