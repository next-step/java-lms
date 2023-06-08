package nextstep.courses.service;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.CourseUser;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.registration.Student;
import nextstep.courses.domain.registration.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public long save(Session session) {
        return sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public Session findById(long id) {
        return sessionRepository.findById(id);
    }

    public void register(Long sessionId, NsUser nsUser) {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findAllBySessionId(sessionId);
        session = session.migrationStatus();
        sessionRepository.updateSessionStatus(session);
        Student student = session.register(nsUser, new HashSet<>(students));
        studentRepository.save(student);
    }

    public void approve(Long sessionId, Long userId) {
        Session session = sessionRepository.findById(sessionId);

        CourseUser courseUser = courseRepository.findByUserId(userId);
        Student student = studentRepository.findByUserId(userId);
        student = session.approve(student, courseUser);
        studentRepository.updateStatus(student);
    }

}
