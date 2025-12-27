package application.query.port;

import domain.model.User;

import java.util.List;
import java.util.Map;

public interface LoadUsersByIdsPort {
    Map<Long, User> loadByIds(List<Long> ids);
}
