package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

public class FreeSession extends Session {

    private final long fee = 0;
    private final int maxEnrollments = 0;

    public FreeSession(long id, long courseId, SessionBody sessionBody, SessionEnrollment sessionEnrollment) {
        super(id, courseId, sessionBody, sessionEnrollment);
    }

    @Override
    public void enroll(Student student, Payment payment) {
        validateSessionStatus();

        sessionEnrollment.enrollStudent(student);
    }

    @Override
    public long getFee() {
        return 0;
    }

    @Override
    public int getMaxEnrollments() {
        return 0;
    }
}
