package nextstep.courses.domain;

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


    public Session(Long id, Long courseId, SessionType type, SessionCoverImage coverImage, SessionEnrollment enrollment) {
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public Session(Long courseId, SessionType type, SessionCoverImage coverImage, SessionEnrollment enrollment) {
        this.courseId = courseId;
        this.type = type;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public void enroll(NsUser nsUser, Payment payment) {
        enrollment.enroll(nsUser, payment);
    }

}
