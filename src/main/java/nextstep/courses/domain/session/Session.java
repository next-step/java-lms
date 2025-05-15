package nextstep.courses.domain.session;

import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.session.enrollment.Enrollments;
import nextstep.courses.domain.session.enrollment.FreeEnrollments;
import nextstep.courses.domain.session.enrollment.PaidEnrollments;
import nextstep.courses.domain.session.info.SessionInfo;

@Getter
@Builder(toBuilder = true)
public class Session {
    private final SessionId id;
    private final SessionInfo info;

    public Session(SessionId id, SessionInfo info) {
        this.id = id;
        this.info = info;
    }

    public boolean isPaid() {
        return info.isPaid();
    }

    public Enrollments createEnrollments() {
        if (info.isPaid()) {
            return new PaidEnrollments(info.getMaxEnrollment());
        }
        return new FreeEnrollments();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id.equals(session.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}