package nextstep.courses.service;

import nextstep.courses.SessionNotFoundException;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session findSessionById(long sessionId) {
        final Session session = sessionRepository.findById(sessionId);

        if (session == null) {
            throw new SessionNotFoundException("강의 정보를 확인할 수 없습니다.");
        }

        return session;
    }
}
