package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SessionService {
    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    public void registerStudent(long sessionId, long userId) {
        Session session = findById(sessionId);
        Student student = session.register(userId);
        studentRepository.save(student);
    }

    @Transactional
    public Session findById(long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        session.updateStudents(students);
        return session;
    }
}
