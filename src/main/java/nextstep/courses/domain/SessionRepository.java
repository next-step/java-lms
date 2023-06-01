package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    Session save(Session session);

    Session findById(Long id);

    long saveNsUser(Session session, NsUser nsUser);

    List<NsUser> findAllUserBySessionId(Long sessionId);
}
