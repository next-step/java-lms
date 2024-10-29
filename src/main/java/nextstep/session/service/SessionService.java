package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SubscribeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void save(Session session) {
        sessionRepository.save(session);
    }

    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public void changeSubscribeStatus(Long sessionId, SubscribeStatus subscribeStatus) {
        sessionRepository.updateSubscribeStatus(sessionId, subscribeStatus);
    }

}
