package nextstep.courses.service;

import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Enrollments;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollments findRequested() {
        return enrollmentRepository.findByStatus(EnrollmentStatus.REQUESTED);
    }

    public Enrollments findRequestedBySessionId(Long sessionId) {
        return enrollmentRepository.findBySessionIdAndStatus(sessionId, EnrollmentStatus.REQUESTED);
    }

}
