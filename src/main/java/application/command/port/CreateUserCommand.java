package application.command.port;

import domain.model.User;

public interface CreateUserCommand {

    User execute(String email, String name);
}
