package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class EnrollmentService {

    public Enrollment enroll(Payment payment, Session session, NsUser nsUser) {
        Enrollment enrollment = Enrollment.enroll(payment, session, nsUser);
        // TODO 수강신청내역 저장
        return enrollment;
    }
}
