package nextstep.courses.application;

import nextstep.courses.domain.Session.Session;
import nextstep.courses.domain.Session.SessionRepository;
import nextstep.courses.domain.Session.Student;
import nextstep.courses.domain.Session.StudentRepository;
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
