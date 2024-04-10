package nextstep.sessions.service;

import nextstep.sessions.controller.SessionEnrollRequestDto;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enroll(SessionEnrollRequestDto request, NsUser requestUser) {
        final Session session = sessionRepository.findById(request.sessionId());
        session.assertCanEnroll(requestUser, request.requestDateTime());
    }

}
