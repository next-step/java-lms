package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.service.request.SessionFindRequest;
import nextstep.session.service.request.SessionRequest;
import nextstep.session.service.request.SessionStatusRequest;
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

    public void save(SessionRequest sessionRequest) {
        Session session = sessionRequest.toDomain();
        sessionRepository.save(session);
    }

    public Session findById(SessionFindRequest sessionFindRequest) {
        return sessionRepository.findById(sessionFindRequest.getSessionId());
    }

    public void changeSubscribeStatus(SessionStatusRequest sessionStatusRequest) {
        sessionRepository.updateSubscribeStatus(sessionStatusRequest.getSessionId(), sessionStatusRequest.getSubscribeStatus());
    }

}
