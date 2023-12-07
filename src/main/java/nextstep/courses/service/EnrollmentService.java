package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.PaidSessionRepository;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private PaidSessionRepository sessionRepository;

    public Enrollment enroll(NsUser nsUser, Long sessionId, Payment payment) {
//        sessionRepository.findById(sessionId);
//        session.enroll(payment);
//
//        return new Enrollment(nsUser.getId(), session.id());
        return null;
    }
}
