package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<NextStepUser> findByUserId(String userId);
}
