package nextstep.courses.service;

import nextstep.courses.SessionNotFoundException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session findSessionById(long sessionId) {
        return Optional.ofNullable(sessionRepository.findById(sessionId))
                .orElseThrow(() -> new SessionNotFoundException("강의 정보를 확인할 수 없습니다."));
    }
}
