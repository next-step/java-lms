package nextstep.courses.service;

import nextstep.common.exception.UserNotFoundException;
import nextstep.courses.domain.EnrolledStudent;
import nextstep.courses.domain.EnrolledStudentRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

import java.time.LocalDateTime;

public class EnrollmentService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final EnrolledStudentRepository enrolledStudentRepository;

    public EnrollmentService(SessionRepository sessionRepository, UserRepository userRepository, PaymentRepository paymentRepository, EnrolledStudentRepository enrolledStudentRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.enrolledStudentRepository = enrolledStudentRepository;
    }

    public void enroll(Long sessionId, String userId, Long paymentId) {
        Session session = sessionRepository.findById(sessionId);
        NsUser user = userRepository.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);
        Payment payment = paymentRepository.findById(paymentId);
        EnrolledStudent enrolledStudent = session.enroll(user, payment.getAmount(), LocalDateTime.now());
        enrolledStudentRepository.save(enrolledStudent);
    }

}
