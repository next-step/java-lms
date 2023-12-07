package nextstep.courses.application;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionService {
    private SessionRepository sessionRepository;

    private StudentRepository studentRepository;

    public void enrollWithORM(NsUser loginUser, Long sessionId) throws AlreadyEnrollmentException {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(loginUser);
    }

    public void enrollWithoutORM1(NsUser loginUser, Long sessionId) throws AlreadyEnrollmentException {
        Session session = sessionRepository.findById(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(loginUser, students);
        studentRepository.save(student);
    }

    public void enrollWithoutORM2(NsUser loginUser, Long sessionId) throws AlreadyEnrollmentException {
        SessionEntity session = sessionRepository.findByIdWithoutORM(sessionId);
        List<Student> students = studentRepository.findBySessionId(sessionId);
        Enrollment enrollment = session.enrollment(students);
        Student student = new Student(loginUser.getId(), sessionId);
        enrollment.enroll(student);
        studentRepository.save(student);
    }
}
