package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.enrollment.Student;
import nextstep.users.domain.NsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Transactional
@Service("sessionService")
public class SessionService {

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final StudentRepository studentRepository;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    public int createSession(Session session) {
        return this.sessionRepository.save(session);
    }

    public Session findBySessionId(Long sessionId) {
        return this.sessionRepository.findById(sessionId);
    }

    public List<Session> findByCourseId(Long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    public void registerStudent(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId);
        Student student = session.register(nsUser);
        studentRepository.save(student);
    }

}
