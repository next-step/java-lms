package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(String userId);
    Optional<User> findById(long id);
}
