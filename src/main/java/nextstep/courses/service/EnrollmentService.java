package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private PaidSessionRepository paidSessionRepository;
    private FreeSessionRepository freeSessionRepository;
    private EnrollmentRepository enrollmentRepository;

    public void freeSessionEnroll(Long userId, Long sessionId, Payment payment) {
        FreeSession freeSession = freeSessionRepository.findById(sessionId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));

        freeSession.enroll(payment);

        Enrollment enrollment = new Enrollment(userId, sessionId);
        enrollmentRepository.save(enrollment);
    }

    public void paidSessionEnroll(Long courseId, Long userId, Long sessionId, Payment payment) {
        PaidSession paidSession = paidSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));

        paidSession.enroll(payment);
        paidSessionRepository.save(courseId, paidSession);

        Enrollment enrollment = new Enrollment(userId, sessionId);
        enrollmentRepository.save(enrollment);
    }
}
