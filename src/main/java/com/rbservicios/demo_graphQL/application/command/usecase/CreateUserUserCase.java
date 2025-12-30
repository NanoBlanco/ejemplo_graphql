package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.CreateUserCommand;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;

public class CreateUserUserCase implements CreateUserCommand {

    private final UserRepository repository;

    public CreateUserUserCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(String email, String name) {
        User saved = new User(
                null,
                email,
                name
        );

        User persisted = repository.save(saved);

        if (persisted == null || persisted.getId() == null) {
            throw new IllegalStateException("User Id was not generated");
        }

        System.out.println("El ID es: " + persisted.getId());

        return persisted;
    }
}
