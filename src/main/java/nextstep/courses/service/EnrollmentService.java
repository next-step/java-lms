package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public void enroll(Session session, Student student, Payment payment) {
        Enrollment enrollment = session.enroll(student, payment);
        enrollmentRepository.save(enrollment);
    }
}
