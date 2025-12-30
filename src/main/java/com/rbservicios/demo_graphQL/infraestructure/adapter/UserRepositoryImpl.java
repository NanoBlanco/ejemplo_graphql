package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.UserEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.UserMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.UserJpaRepository;

public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    public UserRepositoryImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(
                user.getEmail(),
                user.getName()
        );
        return UserMapper.toDomain(repository.save(entity));
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = repository.findById(id).orElse(null);
        User user = UserMapper.toDomain(entity);
        return user;
    }
}
