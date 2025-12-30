package com.rbservicios.demo_graphQL.application.command.port;

import com.rbservicios.demo_graphQL.domain.model.User;

public interface CreateUserCommand {

    User execute(String email, String name);
}
