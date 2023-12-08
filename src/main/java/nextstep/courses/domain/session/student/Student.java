package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Student {

    private Long id;
    private Session session;
    private NsUser user;

    public Student(Session session, NsUser user) {
        this.session = session;
        this.user = user;
    }

    public Long sessionId() {
        return this.session.id();
    }

    public Long nsUserId() {
        return this.user.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(session, student.session) && Objects.equals(user, student.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, user);
    }
}
