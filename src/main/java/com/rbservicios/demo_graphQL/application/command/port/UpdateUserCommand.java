package com.rbservicios.demo_graphQL.application.command.port;

import com.rbservicios.demo_graphQL.domain.model.User;

public interface UpdateUserCommand {
    User execute(Long id, String email, String name);
}
