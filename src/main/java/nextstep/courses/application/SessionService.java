package nextstep.courses.application;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;

public class SessionService {
    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    public void enroll(NsUser loginUser, Long sessionId) throws AlreadyEnrollmentException {
//        Students students = new Students(studentRepository.findBySessionId(sessionId));
//        Session session = sessionRepository.findById(sessionId);
//        Student student = session.enroll(loginUser, students);
//        studentRepository.save(student);
    }
}
