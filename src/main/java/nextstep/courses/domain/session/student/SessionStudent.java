package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionStudent {

    private Long id;
    private Session session;
    private NsUser stuent;

    protected SessionStudent(Session session, NsUser stuent) {
        this.session = session;
        this.stuent = stuent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionStudent that = (SessionStudent) o;
        return Objects.equals(id, that.id) && Objects.equals(session, that.session) && Objects.equals(stuent, that.stuent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, stuent);
    }
}
