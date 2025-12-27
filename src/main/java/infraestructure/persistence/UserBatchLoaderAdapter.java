package infraestructure.persistence;

import application.query.port.LoadUsersByIdsPort;
import domain.model.User;
import infraestructure.persistence.mapper.UserMapper;
import infraestructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserBatchLoaderAdapter implements LoadUsersByIdsPort {

    private final UserJpaRepository repository;
    private final UserMapper mapper;

    public UserBatchLoaderAdapter(UserJpaRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Map<Long, User> loadByIds(List<Long> ids) {
        return repository.findByIdIn(ids).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
