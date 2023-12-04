package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<NsUser> findById(Long id);
    Optional<NsUser> findByUserId(String userId);
}
