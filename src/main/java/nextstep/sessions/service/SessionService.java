package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

@Service("sessionService")
public class SessionService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    public void enrollStudent(Long sessionId, String userId) {
        Session session = getSessionById(sessionId);
        NsUser nsUser = getUserById(userId);

        Session newSession = getSessionOfStudent(session);
        newSession.enrollStudent(nsUser);
        sessionRepository.enrollUser(newSession.getId(), nsUser);
    }

    @Transactional
    public void saveCoverImage(Long sessionId, SessionCoverImage image) {
        Session session = getSessionById(sessionId);

        Session newSession = getSessionOfCoverImage(session);
        newSession.saveCoverImage(image);
        sessionRepository.saveCoverImage(sessionId, image);
    }

    public Session getSession(Session session){
        return getSessionOfCoverImage(getSessionOfStudent(session));
    }

    public Session getSessionOfCoverImage(Session session) {
        SessionCoverImage sessionCoverImage = sessionRepository.findCoverImageBySessionId(session.getId()).orElse(null);
        return new Session(session, sessionCoverImage);
    }

    public Session getSessionOfStudent(Session session) {
        List<NsUser> users = sessionRepository.findAllUsersBySessionId(session.getId());
        SessionStudents students = new SessionStudents(new HashSet<>(users), session.getSessionStudents().getMaximumCapacity());
        return new Session(session, students);
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }

    public NsUser getUserById(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }
}
