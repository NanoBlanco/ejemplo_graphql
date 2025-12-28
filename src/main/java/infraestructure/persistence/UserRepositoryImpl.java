package infraestructure.persistence;

import domain.model.User;
import domain.repository.UserRepository;
import infraestructure.persistence.entity.UserEntity;
import infraestructure.persistence.mapper.UserMapper;
import infraestructure.persistence.repository.UserJpaRepository;

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
        return UserMapper.toDomain(repository.findById(id).orElse(null));
    }
}
