package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.Session;

public class Sessions {

    private final List<Session> sessions;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void add(Session session) {
        validateDuplicated(session);
        this.sessions.add(session);
    }

    private void validateDuplicated(Session session) {
        if (this.sessions.contains(session)) {
            throw new RuntimeException("이미 포함된 강의입니다.");
        }
    }

    public int size() {
        return this.sessions.size();
    }
}
