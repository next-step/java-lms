package nextstep.courses.application;

import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.enrollment.StudentRepository;
import nextstep.courses.domain.enrollment.Students;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionService {

    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    public void enrollInSession(NsUser user, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        Student student = session.enrollInSession(user, students);
        studentRepository.save(student);
    }
}
