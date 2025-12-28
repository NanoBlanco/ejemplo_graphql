package domain.repository;

import domain.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    User findById(Long id);
}
