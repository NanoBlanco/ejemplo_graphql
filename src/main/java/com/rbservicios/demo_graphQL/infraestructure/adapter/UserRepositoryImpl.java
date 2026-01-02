package com.rbservicios.demo_graphQL.infraestructure.adapter;

import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;
import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.UserEntity;
import com.rbservicios.demo_graphQL.infraestructure.persistence.mapper.UserMapper;
import com.rbservicios.demo_graphQL.infraestructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    public UserRepositoryImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entidad = UserMapper.toEntity(user);
        UserEntity saved = repository.save(entidad);
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
