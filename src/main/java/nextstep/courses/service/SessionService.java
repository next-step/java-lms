package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final JdbcSessionRepository sessionRepository;
    private final JdbcStudentRepository studentRepository;

    public SessionService(JdbcSessionRepository sessionRepository, JdbcStudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    public void register(Long sessionId, NsUser user) {
        Session session = sessionRepository.findById(sessionId);
        Student student = session.register(user);
        studentRepository.save(student);
    }

}
