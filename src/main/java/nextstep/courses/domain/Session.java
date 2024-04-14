package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.entity.BaseEntity;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session extends BaseEntity {

    private Long id;
    private Long courseId;
    private SessionType type;
    private SessionCoverImage coverImage;
    private SessionEnrollment enrollment;

    public Session() {
    }

    public Session(Long id, Long courseId, String type, String status, Integer capacity, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.courseId = courseId;
        this.type = SessionType.convert(type);
        this.enrollment = SessionEnrollmentFactory.get(this.id, this.type, SessionStatus.convert(status), capacity, fee);
    }

    public Session(Session session, List<SessionStudent> students) {
        super(session.createdAt, session.updatedAt);
        this.id = session.id;
        this.courseId = session.courseId;
        this.type = session.type;
        this.enrollment = SessionEnrollmentFactory.get(session, students);
    }

    public Session(Long courseId, SessionType type, SessionCoverImage coverImage, SessionStatus status, int capacity, long fee) {
        this.courseId = courseId;
        this.type = type;
        this.coverImage = coverImage;
        this.enrollment = SessionEnrollmentFactory.get(type, status, capacity, fee);
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

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public SessionEnrollment getEnrollment() {
        return enrollment;
    }

}
