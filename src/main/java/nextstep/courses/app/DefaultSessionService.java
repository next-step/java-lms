package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultSessionService implements SessionService{
    private final SessionRepository sessionRepository;

    public DefaultSessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public long save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session findById(long id) {
        return sessionRepository.findById(id);
    }
}
