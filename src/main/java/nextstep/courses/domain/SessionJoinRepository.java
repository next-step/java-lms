package nextstep.courses.domain;

import java.util.List;

public interface SessionJoinRepository {
    //int save(Session session);
    int save(SessionJoin sessionJoin);
    List<SessionJoin> findAllBySessionId(Long sessionId);

    SessionJoin findBySessionIdAndUserId(Long sessionId, Long userId);

    void updateSessionJoinStatus(SessionJoin sessionJoin);
}
