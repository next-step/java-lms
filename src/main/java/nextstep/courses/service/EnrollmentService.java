package nextstep.courses.service;

import nextstep.courses.domain.Enrollment;
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

    public void approve(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강신청 건 입니다."));

        enrollment.approve();
        enrollmentRepository.updateStatus(enrollment);
    }

    public void reject(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수강신청 건 입니다."));

        enrollment.reject();
        enrollmentRepository.updateStatus(enrollment);
    }

    public Enrollments findRequested() {
        return enrollmentRepository.findByStatus(EnrollmentStatus.REQUESTED);
    }

    public Enrollments findRequestedBySessionId(Long sessionId) {
        return enrollmentRepository.findBySessionIdAndStatus(sessionId, EnrollmentStatus.REQUESTED);
    }

    public void save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }
}
