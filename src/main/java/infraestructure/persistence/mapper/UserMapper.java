package infraestructure.persistence.mapper;

import domain.model.User;
import infraestructure.persistence.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity user) {

        return new User(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
