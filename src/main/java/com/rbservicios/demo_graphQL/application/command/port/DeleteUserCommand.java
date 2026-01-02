package com.rbservicios.demo_graphQL.application.command.port;

public interface DeleteUserCommand {
    void execute(Long id);
}
