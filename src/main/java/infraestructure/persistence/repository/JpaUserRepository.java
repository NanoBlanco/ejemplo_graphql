package infraestructure.persistence.repository;

import infraestructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(Long userId);

}
