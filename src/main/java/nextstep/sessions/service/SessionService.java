package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStudent;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("sessionService")
public class SessionService {
    private SessionRepository sessionRepository;

    private UserRepository userRepository;

    @Transactional
    public void enroll(Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        Optional<NsUser> optionalUser = userRepository.findById(payment.getNsUserId());
        SessionStudent sessionStudent = session.enroll(optionalUser.get());
        sessionRepository.enroll(session, sessionStudent);
    }
}
