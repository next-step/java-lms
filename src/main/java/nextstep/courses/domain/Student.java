package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private final Long studentId;

    private final Set<Session> sessions;

    public Student(Long studentId) {
        this.studentId = studentId;
        this.sessions = new HashSet<>();
    }

    public void enroll(Session session) {
        session.add(this);
        sessions.add(session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(sessions, student.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessions);
    }
}
