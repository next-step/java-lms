package nextstep.courses.service;

import nextstep.courses.domain.session.PayType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudentRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import static nextstep.courses.domain.session.PayType.*;

public class FreeSessionService implements SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final SessionStudentRepository sessionStudentRepository;

    public FreeSessionService(UserRepository userRepository, SessionRepository sessionRepository, SessionStudentRepository sessionStudentRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.sessionStudentRepository = sessionStudentRepository;
    }

    @Override
    public boolean supports(PayType payType) {
        return FREE.equals(payType);
    }

    @Override
    public void enroll(Payment payment) {
        NsUser student = userRepository.findByUserId(String.valueOf(payment.nsUserId()))
            .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다. 사용자 아이디 :: " + payment.nsUserId()));

        Session session = sessionRepository.findBy(payment.sessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + payment.nsUserId()));

        SessionStudent sessionStudent = new SessionStudent(session, student);
        sessionStudentRepository.save(sessionStudent);
        session.enroll(sessionStudent, payment);
    }
}
