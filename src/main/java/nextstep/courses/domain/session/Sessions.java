package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private List<Session> sessions = new ArrayList<>();

    public void addSession(Session session) {
        sessions.add(session);
    }
}
