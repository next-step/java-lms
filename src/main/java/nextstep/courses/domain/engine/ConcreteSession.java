package nextstep.courses.domain.engine;

import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.payments.domain.Payment;

public abstract class ConcreteSession implements Session {

    protected final Long id;
    protected final Long courseId;
    protected final SessionCoverImage coverImage;
    protected final SessionEnrollment enrollment;

    protected ConcreteSession(Long id, Long courseId, SessionCoverImage coverImage, SessionEnrollment enrollment) {
        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
    }

    @Override
    public void enroll(Student student, Payment payment) {
        enrollment.satisfy(student, payment);
    }

}
