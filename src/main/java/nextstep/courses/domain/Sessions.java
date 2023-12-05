package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> sessionList;

    public Sessions() {
        this.sessionList = new ArrayList<>();
    }

    public void addSession (Session session) {
        this.sessionList.add(session);
    }

    public int numbOfSessions() {
        return this.sessionList.size();
    }
}
