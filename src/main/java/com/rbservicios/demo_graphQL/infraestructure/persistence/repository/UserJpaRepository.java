package com.rbservicios.demo_graphQL.infraestructure.persistence.repository;

import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByIdIn(List<Long> ids);
}
