package nextstep.courses.domain;

import java.util.List;

public interface SessionRepository {
    Long save(Session session);

    Session findById(Long id, CoverImage coverImage);

    List<NsUserSession> getNsUserSessions(Long sessionId);

    int saveNsUserSession(NsUserSession nsUserSession);
}
