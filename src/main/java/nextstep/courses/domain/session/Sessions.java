package nextstep.courses.domain.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import nextstep.courses.error.exception.NotExistSession;

public class Sessions {

    private final Map<SessionName, Session> sessions = new HashMap<>();

    public void addSession(SessionName sessionName, Session session) {
        sessions.put(sessionName, session);
    }

    public Session deleteSession(SessionName sessionName) {
        return sessions.remove(sessionName);
    }

    public Session findSession(SessionName sessionName) {
        return Optional.ofNullable(sessions.getOrDefault(sessionName, null))
            .orElseThrow(() -> new NotExistSession(sessionName.getValue()));
    }
}
