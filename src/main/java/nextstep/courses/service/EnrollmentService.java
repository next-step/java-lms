package nextstep.courses.service;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class EnrollmentService {
    public Enrollment enroll(NsUser nsUser, Session session, Payment payment) {
        session.enroll(payment);
        return new Enrollment(nsUser.getId(), session.id());
    }
}
