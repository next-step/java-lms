package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private SessionRepository sessionRepository;
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;

    @Transactional
    public void enroll(Long courseId, Payment payment) {
        Session session = sessionRepository.findById(payment.sessionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));
        NsUser user = userRepository.findById(payment.nsUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 유저 입니다."));

        Enrollment enroll = session.enroll(user, payment);

        sessionRepository.save(courseId, session);
        enrollmentRepository.save(enroll);
    }
}
