package nextstep.users.domain;

import java.util.List;

public interface UserRepository {
    long save(NsUser nsUser);

    NsUser findByUserId(String userId);

    List<NsUser> findByUserIds(List<String> userIds);
}
