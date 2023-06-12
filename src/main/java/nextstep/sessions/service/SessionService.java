package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
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
import java.util.Set;

//유저가 세션에 등록하는 기능을 구현한다고 생각하고 관련된 것을 구현해주세요~!
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

    //session을 가져올 때 수강 정보까지 함께 읽어와 객체를 리턴 해보면 어떨까요?
    @Transactional
    public void enroll(Long sessionId, String userId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
        System.out.println("session = " + session);
        NsUser nsUser = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다."));
        enrollStudent(session, nsUser);
    }

    public void enrollStudent(Session session, NsUser nsUser) {
        Session newSession = getSessionOfStudent(session);
        newSession.enrollStudent(nsUser);
        sessionRepository.enrollUser(newSession.getId(), nsUser);
    }

    public Session getSessionOfStudent(Session session) {
        List<NsUser> users = sessionRepository.findAllUsersBySessionId(session.getId());
        SessionStudents students = new SessionStudents(new HashSet<>(users), session.getSessionStudents().getMaximumCapacity());
        return new Session(session, students);
    }


}
