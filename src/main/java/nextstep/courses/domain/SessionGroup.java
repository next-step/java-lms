package nextstep.courses.domain;

import java.util.List;

public class SessionGroup {

    private final List<SessionWithImage> sessions;

    public SessionGroup(List<SessionWithImage> sessions) {
        this.sessions = sessions;
    }
}
