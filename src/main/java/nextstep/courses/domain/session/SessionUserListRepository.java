package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionUserListRepository {
    int save(Long userId, Long sessionId);

    List<NsUser> findAllBySessionId(Long sessionId);
}
