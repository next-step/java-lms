package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.dto.CreateSessionRequest;
import nextstep.session.dto.EnrollSessionRequest;
import nextstep.session.repository.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserService userService;

    public SessionService(SessionRepository sessionRepository, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
    }

    public void save(CreateSessionRequest request) {
        sessionRepository.save(request.toEntity());
    }

    public Session findSession(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public void enrollSession(EnrollSessionRequest request) {
        Session session = findSession(request.getSessionId());
        NsUser user = userService.findUser(request.getUserId());
        session.enrollSession(request.getFee(), user);
    }
}
