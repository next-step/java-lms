package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sessions {

    private List<Session> sessions;

    private Sessions(Session... sessions) {
        this.sessions = new ArrayList<>(Arrays.asList(sessions));
    }

    public static Sessions from(Session session) {
        return new Sessions(session);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
}
