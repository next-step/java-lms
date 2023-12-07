package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session {

    private Long id;

    private Long courseId;

    private final SessionImage sessionImage;

    private final SessionPeriod sessionPeriod;

    private final Enrollment enrollment;

    public Session(Long id) {
        this(id, 1L,
                new SessionImage(0, "jpg", 300, 200),
                new SessionPeriod(
                        LocalDate.of(2023, 1, 1),
                        LocalDate.of(2023, 1, 30)
                ),
                new Enrollment(
                        new SessionPrice(1000),
                        SessionStatus.PREPARE,
                        SessionRecruitment.OPEN,
                        SessionType.PAID,
                        new SessionUserCount(0, 0)
                )
        );
    }

    public Session(Long id, Long courseId, SessionImage sessionImage, SessionPeriod sessionPeriod, Enrollment enrollment) {
        this.id = id;
        this.courseId = courseId;
        this.sessionImage = sessionImage;
        this.sessionPeriod = sessionPeriod;
        this.enrollment = enrollment;
    }

    public void register(NsUser user, Payment payment) {
        enrollment.enroll(user, payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
