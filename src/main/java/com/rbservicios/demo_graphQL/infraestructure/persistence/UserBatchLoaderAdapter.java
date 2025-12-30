package com.rbservicios.demo_graphQL.infraestructure.persistence;

import com.rbservicios.demo_graphQL.application.query.port.LoadUsersByIdsPort;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.UserMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserBatchLoaderAdapter implements LoadUsersByIdsPort {

    private final UserJpaRepository repository;

    public UserBatchLoaderAdapter(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<Long, User> loadByIds(List<Long> ids) {
        return repository.findByIdIn(ids).stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toMap(User::getId, user -> user));
    }
}
