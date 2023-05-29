package nextstep.lms.service;

import nextstep.lms.domain.Enrollment;
import nextstep.lms.domain.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("enrollmentService")
public class EnrollmentService {
    @Resource(name = "enrollmentRepository")
    private EnrollmentRepository enrollmentRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }
}
