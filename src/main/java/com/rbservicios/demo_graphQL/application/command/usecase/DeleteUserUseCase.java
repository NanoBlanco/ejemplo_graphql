package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.DeleteUserCommand;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;

public class DeleteUserUseCase implements DeleteUserCommand {

    private final UserRepository repository;

    public DeleteUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        if(!repository.findById(id).isPresent()) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }
}
