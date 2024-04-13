package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private final Long id;
    private final Long courseId;
    private final SessionCoverImage coverImage;
    private final SessionEnrollment enrollment;


    public Session(Long id, Long courseId, SessionCoverImage coverImage, SessionEnrollment enrollment) {
        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    public void enroll(NsUser nsUser, Payment payment) {
        enrollment.enroll(nsUser, payment);
    }

}
