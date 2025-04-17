package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions;

    public Sessions() {
        sessions = new ArrayList<>();
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
