package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionAttendeeRepository {
    int save(Session session, NsUser user);

    List<String> findBySessionId(Long sessionId);
}
