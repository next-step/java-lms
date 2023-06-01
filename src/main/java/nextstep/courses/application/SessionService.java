package nextstep.courses.application;

import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.enrollment.StudentRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    public void enrollInSession(NsUser user, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Student student = session.enrollInSession(user);
        studentRepository.save(student);
    }
}
