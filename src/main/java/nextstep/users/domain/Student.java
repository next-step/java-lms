package nextstep.users.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Sessions;

import java.util.Objects;

public class Student {
    private NsUser nsUser;
    private Sessions sessions;

    public Student(NsUser nsUser) {
        this(nsUser, new Sessions());
    }

    public Student(NsUser nsUser, Sessions sessions) {
        this.nsUser = nsUser;
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.addSession(session);
    }

    public Sessions getSessions() {
        return sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nsUser, student.nsUser) && Objects.equals(sessions, student.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUser, sessions);
    }
}
