package com.rbservicios.demo_graphQL.adapter.graphql.resolver;

import com.rbservicios.demo_graphQL.adapter.graphql.context.UserContext;
import graphql.schema.DataFetchingEnvironment;

public abstract class BaseGraphQLResolver {

    protected UserContext getUserContext(DataFetchingEnvironment env) {
        return env.getGraphQlContext().get("userContext");
    }
}
