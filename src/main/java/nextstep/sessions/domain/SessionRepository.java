package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {

    int save(Session session);

    Session findById(Long id);

    int saveUser(Long sessionId, NsUser nsUser);

    List<NsUser> findAllUsersBySessionId(Long SessionId);

}
