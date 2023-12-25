package nextstep.users.domain;

import java.util.Optional;

public interface UserRepository {
    NsUser findByUserId(String userId);
}
