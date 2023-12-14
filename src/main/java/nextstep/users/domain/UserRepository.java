package nextstep.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<NsUser> findByUserId(String userId);

    List<NsUser> findAllByIds(List<Long> ids);
}
