package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private List<Session> sessions;

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int size() {
        return sessions.size();
    }
}
