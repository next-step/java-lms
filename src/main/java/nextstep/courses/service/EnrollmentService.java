package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.enrollment.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.exception.NoSuchEnrollmentException;
import nextstep.courses.exception.NoSuchSessionException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.NoSuchUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             SessionRepository sessionRepository,
                             UserRepository userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void enroll(Long sessionId, String userId) {
        Session session = Optional.ofNullable(sessionRepository.findById(sessionId))
                .orElseThrow(NoSuchSessionException::new);
        NsUser nsUser = userRepository.findByUserId(userId)
                .orElseThrow(NoSuchUserException::new);

        enrollmentRepository.save(new Enrollment(session, nsUser, EnrollmentStatus.PENDING));
    }

    @Transactional
    public void approve(Long id) {
        Enrollment enrollment = Optional.ofNullable(enrollmentRepository.findById(id))
                .orElseThrow(NoSuchEnrollmentException::new);

        enrollment.approve();
        enrollmentRepository.update(enrollment);
    }
}
