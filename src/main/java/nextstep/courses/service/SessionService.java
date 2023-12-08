package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class SessionService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    @Transactional
    public void enroll(Long sessionId, NsUser loginUser, Payment payment) {
        Session session = sessionRepository.findById(sessionId).orElseThrow();
        NsUser student = userRepository.findByUserId(loginUser.getUserId()).orElseThrow();

        session.enrollSession(student, payment.getAmount());
    }
}
