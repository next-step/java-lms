package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private final List<Session> values;

    public Sessions() {
        this.values = new ArrayList<>();
    }

    public void add (Session session) {
        this.values.add(session);
    }

    public int numbOfSessions() {
        return this.values.size();
    }
}
