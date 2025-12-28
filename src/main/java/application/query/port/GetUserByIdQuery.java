package application.query.port;

import domain.model.User;

public interface GetUserByIdQuery {
    User execute(Long id);
}
