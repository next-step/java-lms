package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions = new ArrayList<>();

    public Sessions() {

    }

    public void add(Session session) {
        this.sessions.add(session);
    }
}
