package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {
    private PaidSessionRepository paidSessionRepository;
    private FreeSessionRepository freeSessionRepository;
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;

    public void freeSessionEnroll(Payment payment) {
        FreeSession freeSession = freeSessionRepository.findById(payment.sessionId())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));
        NsUser user = userRepository.findById(payment.nsUserId())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 유저 입니다."));

        freeSession.enroll(payment);

        Enrollment enrollment = new Enrollment(user.getId(), freeSession.id());
        enrollmentRepository.save(enrollment);
    }

    public void paidSessionEnroll(Long courseId, Payment payment) {
        PaidSession paidSession = paidSessionRepository.findById(payment.sessionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));
        NsUser user = userRepository.findById(payment.nsUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 유저 입니다."));

        paidSession.enroll(payment);
        paidSessionRepository.save(courseId, paidSession);

        Enrollment enrollment = new Enrollment(user.getId(), paidSession.id());
        enrollmentRepository.save(enrollment);
    }
}
