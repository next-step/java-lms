package nextstep.courses.service;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
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
    public long enroll(Long courseId, Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId())
                                           .orElseThrow(() -> new IllegalArgumentException("신청할 강의를 확인해주세요."));
        NsUser user = userRepository.findById(payment.getNsUserId())
                                    .orElseThrow(() -> new IllegalArgumentException("유저 정보를 확인해주세요."));
        Enrollment enroll = enroll(session, user, payment);
        sessionRepository.save(courseId, session);
        return enrollmentRepository.save(enroll);
    }

    public Enrollment enroll(final Session session, final NsUser user, final Payment payment) {
        session.validateEnrollState(payment);
        return new Enrollment(user.getId(), payment.getSessionId());
    }

}
