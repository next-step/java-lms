package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class EnrollmentApply {
    private SessionCore sessionCore;
    private Payment payment;
    private Enrollments enrollments;

    public EnrollmentApply(Enrollments enrollments, Payment payment, SessionCore sessionCore) {
        this.enrollments = enrollments;
        this.payment = payment;
        this.sessionCore = sessionCore;
    }

    public Enrollment enroll(NsUser user, Long sessionId) {
        sessionCore.validatePaymentAmount(payment);
        sessionCore.validateNotFull(this.enrollments);
        sessionCore.validateSessionStatus();
        sessionCore.validateRecruitmentStatus();
        enrollments.validateNotDuplicate(new Enrollment(user, sessionId));

        Enrollment enrollment = new Enrollment(user, sessionId, LocalDateTime.now(), null);
        enrollments.add(enrollment);

        return enrollment;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments.getEnrollments();
    }
}
