package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.UpdateUserCommand;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;

public class UpdateUserUseCase implements UpdateUserCommand {

    private final UserRepository repository;

    public UpdateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(Long id, String email, String name) {
        User loaded = repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        if(email != null) {
            loaded.setEmail(email);
        }

        if(name != null) {
            loaded.setName(name);
        }

        return repository.save(loaded);
    }
}
