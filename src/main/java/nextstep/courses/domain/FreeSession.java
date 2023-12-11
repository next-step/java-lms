package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Image image, Enrollment enrollment) {
        super(image, SessionType.FREE, enrollment);
    }

    @Override
    public void enroll(NsUser student, Payment payment) {
        this.getEnrollment().enroll(student);
    }
}
