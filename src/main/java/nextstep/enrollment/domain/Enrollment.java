package nextstep.enrollment.domain;

import nextstep.global.domain.BaseTimeDomain;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment extends BaseTimeDomain {
    private Long id;

    private Session session;

    private NsUser student;

    public static Enrollment of(Session session, NsUser student, LocalDateTime createdDate) {
        return new Enrollment(session, student, createdDate);
    }

    private Enrollment(Session session, NsUser student, LocalDateTime createdDate) {
        this.session = session;
        this.student = student;
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(session, that.session) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, student);
    }
}
