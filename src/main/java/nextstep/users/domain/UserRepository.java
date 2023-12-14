package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    long save(NsUser user);
    NsUser findById(long id);
    Optional<NsUser> findByUserId(String userId);
}
