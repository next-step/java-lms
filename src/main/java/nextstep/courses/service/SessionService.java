package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    public SessionService(SessionRepository sessionRepository,
                          StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    public void enroll(NsUser loginUser,
                       Payment payment,
                       long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Students students = studentRepository.findBySessionId(sessionId);
        Student student = session.enroll(payment, loginUser, students);
        studentRepository.save(student);
    }
}
