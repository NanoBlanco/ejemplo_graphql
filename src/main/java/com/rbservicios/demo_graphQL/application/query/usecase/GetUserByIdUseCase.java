package com.rbservicios.demo_graphQL.application.query.usecase;

import com.rbservicios.demo_graphQL.application.query.port.GetUserByIdQuery;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;

public class GetUserByIdUseCase implements GetUserByIdQuery {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(Long id) {
        return repository.findById(id);
    }
}
