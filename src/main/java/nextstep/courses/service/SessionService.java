package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.value.Money;
import nextstep.payments.domain.Payment;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enroll(Long sessionId, Long studentId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        Enrollment enrollment = session.enroll(studentId, new Money(payment.amount()));
        enrollmentRepository.save(enrollment);
    }
}
