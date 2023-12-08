package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.dto.CreateSessionRequest;
import nextstep.session.repository.SessionRepository;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void save(CreateSessionRequest request) {
        sessionRepository.save(request.toEntity());
    }

    public Session findSession(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
