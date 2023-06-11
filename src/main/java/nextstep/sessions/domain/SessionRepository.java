package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    int save(Session session);

    Optional<Session> findById(Long id);

    int saveUser(Long sessionId, NsUser nsUser);

    List<NsUser> findAllUsersBySessionId(Long SessionId);

}
