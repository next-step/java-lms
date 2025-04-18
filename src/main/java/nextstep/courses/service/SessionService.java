package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    @Transactional
    public void enroll(Long sessionId, Student student, Payment payment) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));

        Enrollments sessionEnrollments = enrollmentRepository.findBySessionId(sessionId)
                .orElse(new Enrollments());

        session.addEnrollments(sessionEnrollments);

        Enrollments userEnrollments = enrollmentRepository.findByUserId(student.getId())
                .orElse(new Enrollments());

        Enrollment enrollment = session.enroll(student, payment);
        enrollmentRepository.save(enrollment);
    }
}
