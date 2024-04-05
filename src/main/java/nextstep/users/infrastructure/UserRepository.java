package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<NsUser> findByUserId(String userId);

    Optional<NsUser> findById(Long id);

    List<NsUser> findByIds(List<Long> ids);

    long save(NsUser nsUser);

    int update(NsUser nsUser);
}
