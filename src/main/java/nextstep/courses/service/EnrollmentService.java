package nextstep.courses.service;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service("enrollmentsService")
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void approveEnrollment(Long enrollmentId, NsUser loginUser) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        enrollment.approve(loginUser);
        enrollmentRepository.update(enrollment);
    }

    public void rejectEnrollment(Long enrollmentId, NsUser loginUser) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        enrollment.reject(loginUser);
        enrollmentRepository.update(enrollment);
    }

    public void waitEnrollment(Long enrollmentId, NsUser loginUser) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        enrollment.wait(loginUser);
        enrollmentRepository.update(enrollment);
    }
}
