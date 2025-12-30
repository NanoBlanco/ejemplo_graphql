package com.rbservicios.demo_graphQL.infraestructure.persistence.repository;

import com.rbservicios.demo_graphQL.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(Long userId);

}
