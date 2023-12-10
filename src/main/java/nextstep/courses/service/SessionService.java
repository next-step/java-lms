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

    private SessionRepository sessionRepository;

    private ImageRepository imageRepository;

    private UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, ImageRepository imageRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
        if(session.images() != null) {
            imageRepository.saveAll(session.images());
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
