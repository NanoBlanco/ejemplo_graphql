package com.rbservicios.demo_graphQL.domain.repository;

import com.rbservicios.demo_graphQL.domain.model.User;

public interface UserRepository {

    User save(User user);
    User findById(Long id);
}
