package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
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

    public void enroll(NsUser nsUser,
                       Payment payment,
                       long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Student student = session.enrol(payment, nsUser);
        studentRepository.save(student);
    }
}
