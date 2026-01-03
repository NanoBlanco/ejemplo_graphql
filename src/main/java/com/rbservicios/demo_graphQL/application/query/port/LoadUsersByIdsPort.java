package com.rbservicios.demo_graphQL.application.query.port;

import com.rbservicios.demo_graphQL.domain.model.User;
import java.util.List;
import java.util.Map;

public interface LoadUsersByIdsPort {
    Map<Long, User> loadByIds(List<Long> ids);
}
