package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.enrollment.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("sessionService")
public class SessionService {

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final StudentService studentService;

    public SessionService(SessionRepository sessionRepository, StudentService studentService) {
        this.sessionRepository = sessionRepository;
        this.studentService = studentService;
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

    public Session registerStudent(Long sessionId, Long studentId) {
        Session session = sessionRepository.findById(sessionId);
        Student student = studentService.findByStudentId(studentId);

        session.enroll(student);
        return session;
    }

}
