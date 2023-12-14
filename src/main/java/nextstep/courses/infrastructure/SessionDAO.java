package nextstep.courses.infrastructure;

import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.session.Session;

import java.util.List;

public interface SessionDAO {
    Long save(Session session);

    Session findById(Long id);

    List<NsUserSession> findNsUserSessionsBySessionId(Long sessionId);

    int saveNsUserSession(NsUserSession nsUserSession);

    int updateNsUserSession(NsUserSession nsUserSession);
}
