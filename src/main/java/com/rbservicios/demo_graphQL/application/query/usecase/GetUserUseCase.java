package com.rbservicios.demo_graphQL.application.query.usecase;

import com.rbservicios.demo_graphQL.application.query.port.GetUserQuery;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCase implements GetUserQuery {

    private final UserRepository repository;

    public GetUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
