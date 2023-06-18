package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public int createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Transactional
    public Session findById(Long id) {
        return sessionRepository.findById(id);
    }

    @Transactional
    public List<Session> findByCourseId(Long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    @Transactional
    public Session registerStudent(Long sessionId, Long studentId) {
        Session session = sessionRepository.findById(sessionId);
        Student student = studentService.findById(studentId);

        session.add(student);
        return session;
    }
}
