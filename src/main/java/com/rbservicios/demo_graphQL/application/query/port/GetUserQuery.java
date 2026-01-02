package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.User;

import java.util.List;

public interface GetUserQuery {
    User getById(Long id);
    List<User> getAll();
}
