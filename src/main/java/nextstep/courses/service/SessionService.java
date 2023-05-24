package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;

import java.util.HashSet;
import java.util.Set;

public class SessionService {

    private final Set<Session> sessions = new HashSet<>();
    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void registerSession(Long id) {
        Session session = sessionRepository.findById(id);
        System.out.println("session = " + session);

        if (session.isRecruiting()) {
//            sessionRepository.save(session);
            this.sessions.add(session);
        }
    }


    int count() {
        return sessions.size();
    }
}
