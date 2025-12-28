package application.query.usecase;

import application.query.port.GetUserByIdQuery;
import domain.model.User;
import domain.repository.UserRepository;

public class GetUserByIdUseCase implements GetUserByIdQuery {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(Long id) {
        return repository.findById(id);
    }
}
