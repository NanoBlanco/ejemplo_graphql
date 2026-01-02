package com.rbservicios.demo_graphQL.application.command.port;

public interface DeleteProductCommand {
    void execute(Long id);
}
