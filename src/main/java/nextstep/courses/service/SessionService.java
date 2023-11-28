package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.exception.NotFoundStudentException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    public SessionService(SessionRepository sessionRepository,
                          StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void enroll(NsUser loginUser,
                       Payment payment,
                       long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Students students = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(payment, loginUser, students);
        studentRepository.save(student);
    }

    @Transactional
    public void approve(long studentId,
                        long sessionId) {
        Student student = studentRepository.findByIdAndSessionId(studentId, sessionId).orElseThrow(() -> new NotFoundStudentException("강의 신청한 학생을 찾을 수 없습니다."));
        student.approve();

        studentRepository.updateSelection(student);
    }
}
