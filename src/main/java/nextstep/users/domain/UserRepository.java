package nextstep.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<NsUser> findByUserCode(UserCode userCode);

    NsUser save(NsUser user);

    List<NsUser> findAll();
}
