package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private final Set<Session> sessions = new HashSet<>();

    public Student() {
    }

    public void enroll(Session session) {
        session.add(this);
        sessions.add(session);
    }
}
