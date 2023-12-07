package nextstep.courses.service;

import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
        if(session.image() != null) {
            imageRepository.save(session.image());
        }
    }

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        Session findSession = sessionRepository.findById(id);
        findSession.mappadByImage(imageRepository.findBySessionId(id));
        return findSession;
    }

    @Transactional
    public void SessionPayment(Payment payment) {
        Session session = sessionRepository.findById(payment.sessionId());
        NsUser nsUser = userRepository.findById(payment.nsUserId()).orElseThrow(IllegalArgumentException::new);
        session.addParticipant(payment.amount(), nsUser);
        sessionRepository.save(session);
    }
}
