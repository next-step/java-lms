package nextstep.courses.infrastructure;

import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.Session;

import java.util.List;

public interface SessionDAO {
    Long save(Session session);

    Session findById(Long id);

    List<NsUserSession> getNsUserSessions(Long sessionId);

    int saveNsUserSession(NsUserSession nsUserSession);
}
