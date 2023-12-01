package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class Sessions {

    private final List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(this.sessions);
    }
}
