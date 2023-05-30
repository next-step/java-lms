package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CoverImage coverImage;
    private PaymentType paymentType;
    private SessionEnrollment sessionEnrollment;

    public Session(Long id, Long courseId, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, PaymentType paymentType, SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionEnrollment = sessionEnrollment;
    }

    public void register(NsUser user) {
        sessionEnrollment.enroll(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt) && Objects.equals(coverImage, session.coverImage) && paymentType == session.paymentType && Objects.equals(sessionEnrollment, session.sessionEnrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, createdAt, updatedAt, coverImage, paymentType, sessionEnrollment);
    }
}
