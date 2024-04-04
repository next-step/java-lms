package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.SessionListener;

import java.util.List;

public interface SessionListenerRepository {
    int save(SessionListener sessionListener);

    void saveAll(List<SessionListener> sessionListeners);

    List<SessionListener> findAllBySessionId(long sessionId);

    int delete(SessionListener sessionListener);
}
