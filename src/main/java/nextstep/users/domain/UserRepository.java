package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<NsUser> findByUserId(long userId);
}
