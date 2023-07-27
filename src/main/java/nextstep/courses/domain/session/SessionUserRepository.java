package nextstep.courses.domain.session;

import java.util.List;

public interface SessionUserRepository {

    int save(SessionUser user);
    List<SessionUser> findBySessionId(Long sessionId);
}
