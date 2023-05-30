package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.exception.SessionNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public long save(Session session) {
        return sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(id));
    }

}
