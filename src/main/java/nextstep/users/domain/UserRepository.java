package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    int save(NsUser user);

    Optional<NsUser> findByUserId(String userId);
}
