package nextstep.courses.service;

import nextstep.courses.domain.session.PayType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import static nextstep.courses.domain.session.PayType.*;

public class PaySessionService implements SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public PaySessionService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean supports(PayType payType) {
        return PAY.equals(payType);
    }

    @Override
    public void enroll(Payment payment) {
        NsUser student = userRepository.findByUserId(String.valueOf(payment.nsUserId()))
            .orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다. 사용자 아이디 :: " + payment.nsUserId()));

        Session session = sessionRepository.findBy(payment.sessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + payment.nsUserId()));

        session.enroll(student, payment);
    }
}
