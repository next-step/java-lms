package nextstep.session.service;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionCoverImage;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudentRepository;
import nextstep.session.domain.student.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionStudentService studentService;
    //private final SessionTeacherService sessionTeacherService;

    public SessionService(
            SessionRepository jdbcSessionRepository, SessionStudentService sessionStudentService
    ) {
        this.sessionRepository = jdbcSessionRepository;
        this.studentService = sessionStudentService;
    }
    @Transactional
    public Long enrollStudent(NsUser studentUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
        SessionStudents studentsOfSession = studentService.getStudentsOfSession(session);
        Session session1 = new Session(session, studentsOfSession);
        return studentService.enrollStudent(session1, studentUser);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Session session) {
        sessionRepository.save(session);
    }

    /*@Transactional
    public void enrollStudent(Long sessionId, NsUser nsUser) {
        Session session = getSessionById(sessionId);

        Session newSession = getSessionOfStudent(session);
        newSession.enrollStudent(nsUser);
        studentService.enrollStudent(newSession, nsUser);
    }*/

    @Transactional
    public void saveCoverImage(Long sessionId, SessionCoverImage image) {
        Session session = getSessionById(sessionId);

        Session newSession = getSessionOfCoverImage(session);
        newSession.saveCoverImage(image);
        sessionRepository.saveCoverImage(sessionId, image);
    }

    public Session getSessionOfStudent(Session session) {
        SessionStudents sessionStudents = studentService.getStudentsOfSession(session);
        return new Session(session, sessionStudents);
    }

    public Session getSession(Session session){
        return getSessionOfCoverImage(getSessionOfStudent(session));
    }

    public Session getSessionOfCoverImage(Session session) {
        SessionCoverImage sessionCoverImage = sessionRepository.findCoverImageBySessionId(session.getId()).orElse(null);
        return new Session(session, sessionCoverImage);
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }

    /*public NsUser getUserById(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }*/
}
