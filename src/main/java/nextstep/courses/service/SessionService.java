package nextstep.courses.service;

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

    public SessionService(SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
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
        Student student = session.register(nsUser, new HashSet<>(students));
        studentRepository.save(student);
    }

}
