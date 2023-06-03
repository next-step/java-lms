package nextstep.courses.domain;

import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    long save(Session session);

    long saveSessionUser(Session session, NsUser nsUser);

    Session findById(Long sessionId);

    List<Long> findSessionUserIdsBySessionId(Long sessionId);
}
