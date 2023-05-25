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

    public void approveStudent(NsUser loginUser, Long sessionId, Long studentId) {
        checkInstructorRole(loginUser, sessionId);
        Student student = studentRepository.findById(studentId);
        student.approve();
        studentRepository.save(student);
    }

    private void checkInstructorRole(NsUser loginUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        if (!session.isOwner(loginUser)) {
            throw new HasNotPermissionException("학생 승인은 해당 강의의 강사만 가능합니다.");
        }
    }

    public void disApproveStudent(NsUser loginUser, Long sessionId, Long studentId) {
        checkInstructorRole(loginUser, sessionId);
        Student student = studentRepository.findById(studentId);
        student.disApprove();
        studentRepository.save(student);
    }
}
