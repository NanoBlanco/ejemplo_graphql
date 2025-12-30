package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.User;

public interface GetUserByIdQuery {
    User execute(Long id);
}
