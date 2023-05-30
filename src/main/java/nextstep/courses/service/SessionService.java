package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Transactional
    public int createSession(Session session, Long courseId) {
        return sessionRepository.save(session, courseId);
    }

//    public int registerSession(NsUser nsUser, int sessionId) {
//        Session session = findById(sessionId);
//        session.register(nsUser);
//        return 0;
//    }

    public Session findById(int sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
