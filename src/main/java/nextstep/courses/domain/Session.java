package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.SessionPeriod;
import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.entity.BaseEntity;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private Long id;
    private Long courseId;
    private SessionType type;
    private SessionPeriod period;
    private SessionCoverImage coverImage;
    private SessionEnrollment enrollment;

    public Session(Long id, Long courseId, SessionType type, SessionPeriod period, SessionCoverImage coverImage, SessionEnrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.period = period;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public Session(Long sessionId, Long courseId, SessionType type, SessionPeriod period, SessionEnrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = sessionId;
        this.courseId = courseId;
        this.type = type;
        this.period = period;
        this.enrollment = enrollment;
    }

    public Session(Long courseId, SessionType type, SessionPeriod period, SessionEnrollment enrollment) {
        this.courseId = courseId;
        this.type = type;
        this.period = period;
        this.enrollment = enrollment;
    }


    public SessionStudent enroll(NsUser nsUser, Payment payment) {
        return enrollment.enroll(nsUser, payment);
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

    public SessionPeriod getPeriod() {
        return period;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public SessionEnrollment getEnrollment() {
        return enrollment;
    }

}
