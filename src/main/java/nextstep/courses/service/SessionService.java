package nextstep.courses.service;

import nextstep.courses.domain.session.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public int save(SessionParticipant participant, SessionCondition condition, SessionTerm term, Long courseId) {
        return sessionRepository.save(participant, condition, term, courseId);
    }

    @Transactional(readOnly = true)
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
