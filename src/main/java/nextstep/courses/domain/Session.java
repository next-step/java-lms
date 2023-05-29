package nextstep.courses.domain;

import nextstep.courses.domain.base.BaseDate;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session extends BaseDate {

    private Long id;

    private Course course;

    private SessionDate sessionDate;

    private String imageUrl;

    private boolean paid;

    private SessionApply sessionApply;

    public static Session open(Long id, Course course,
                               LocalDate sessionStartDate,
                               LocalDate sessionEndDate,
                               String imageUrl,
                               boolean paid,
                               int recruitmentCount) {
        return new Session(id,
                course,
                new SessionDate(sessionStartDate, sessionEndDate),
                imageUrl,
                paid,
                new SessionApply(recruitmentCount));
    }

    private Session(Long id, Course course, SessionDate sessionDate,
                   String imageUrl, boolean paid, SessionApply sessionApply) {
        this.id = id;
        this.course = course;
        this.sessionDate = sessionDate;
        this.imageUrl = imageUrl;
        this.paid = paid;
        this.sessionApply = sessionApply;
    }

    public SessionUser apply(Long id, NsUser user) {
        return sessionApply.apply(id, this, user);
    }

    public void recruiting() {
        sessionApply.recruiting();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
