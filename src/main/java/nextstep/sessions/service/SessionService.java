package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStudent;
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

    @Transactional
    public void approve(Session session, NsUser enrollUser, NsUser loginUser) {
        SessionStudent student = sessionRepository.studentFindBySessionIdAndUserId(session.getId(), enrollUser.getId());
        session.approve(student, loginUser);
        sessionRepository.approvalStudent(student);
    }

    @Transactional
    public void cancel(Session session, NsUser enrollUser, NsUser loginUser) {
        SessionStudent student = sessionRepository.studentFindBySessionIdAndUserId(session.getId(), enrollUser.getId());
        session.cancel(student, loginUser);
        sessionRepository.cancelStudent(student);
    }
}
