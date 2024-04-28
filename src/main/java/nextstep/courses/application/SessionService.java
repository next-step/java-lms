package nextstep.courses.application;

import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private SessionRepository sessionRepository;
    private StudentRepository studentRepository;

    public void apply(NsUser loginUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Student student = new Student(sessionId, loginUser.getId());
        Students students = new Students(studentRepository.findBySessionId(sessionId));

        Enrollment enrollment = session.enroll(students);
        enrollment.enroll(student);
    }
}
