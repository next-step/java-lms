package nextstep.courses.application;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionService {
    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    public void enroll(NsUser loginUser, Long sessionId) throws AlreadyEnrollmentException {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(loginUser, students);
        studentRepository.save(student);
    }
}
