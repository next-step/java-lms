package nextstep.courses.application;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.session.StudentRepository;
import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionService {
    private SessionRepository sessionRepository;
    private StudentRepository studentRepository;

    public void apply(NsUser loginUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        session.apply(students);
    }
}
