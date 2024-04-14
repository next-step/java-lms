package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private final Long courseId;
    private final SessionType type;
    private final SessionCoverImage coverImage;
    private final SessionEnrollment enrollment;

    public Session(Long courseId, SessionType type, SessionCoverImage coverImage, SessionStatus status) {
        this.courseId = courseId;
        this.type = type;
        this.coverImage = coverImage;
        this.enrollment = SessionEnrollmentFactory.get(type, status);
    }

    public Session(Long courseId, SessionType type, SessionCoverImage coverImage, SessionStatus status, int capacity, long fee) {
        this.courseId = courseId;
        this.type = type;
        this.coverImage = coverImage;
        this.enrollment = SessionEnrollmentFactory.get(type, status, capacity, fee);
    }

    public void enroll(NsUser nsUser, Payment payment) {
        enrollment.enroll(nsUser, payment);
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionType getType() {
        return type;
    }

    public SessionEnrollment getEnrollment() {
        return enrollment;
    }

}
