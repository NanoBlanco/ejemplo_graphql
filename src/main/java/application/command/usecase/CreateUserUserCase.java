package application.command.usecase;

import application.command.port.CreateUserCommand;
import domain.model.User;
import domain.repository.UserRepository;

public class CreateUserUserCase implements CreateUserCommand {

    private final UserRepository repository;

    public CreateUserUserCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(String email, String name) {
        User saved = new User(
                null,
                email,
                name
        );

        return repository.save(saved);
    }
}
