package domain.repository;

import domain.model.User;

import java.util.List;

public interface UserRepository {

    User findById(Long id);
}
