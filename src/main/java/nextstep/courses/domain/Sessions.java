package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {
    private final List<Session> values;

    public Sessions() {
        this.values = new ArrayList<>();
    }

    public List<Session> values() {
        return Collections.unmodifiableList(values);
    }

    public void addSession(Session session) {
        values.add(session);
    }
}
