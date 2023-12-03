package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class EnrollmentService {

    public void enrollSession(NsUser nsUser, Session session, Payment payment) {
        session.enroll(payment);

    }
}
