package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {

    int save(NsUser nsUser);

    Optional<NsUser> findByUserId(String userId);
}
