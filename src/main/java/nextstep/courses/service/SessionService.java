package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    @Transactional
    public int createSession(Session session, Long courseId) {
        return sessionRepository.save(session, courseId);
    }
    @Transactional
    public int registerSession(int sessionId, long userId) {
        Student student = new Student(sessionId, userId);
        Session session = findById(sessionId);
        session.register(student);
        return studentRepository.save(student);
    }

    public Session findById(int sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
