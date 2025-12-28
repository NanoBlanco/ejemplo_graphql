package adapter.graphql.mapper;

import adapter.graphql.model.UserGql;
import domain.model.User;

public class UserGqlMapper {

    public static UserGql fromDomain(User user) {
        return new UserGql(
                user.getId(),
                user.getEmail(),
                user.getName()
        );

    }
}
