package nextstep.courses.enrollment.service;

import nextstep.courses.course.domain.Course;
import nextstep.courses.course.domain.service.CourseDomainService;
import nextstep.courses.course.service.repository.CourseRepository;
import nextstep.courses.enrollment.domain.Enrollment;
import nextstep.courses.enrollment.service.dto.EnrollmentSaveRequest;
import nextstep.courses.enrollment.service.repository.EnrollmentRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.repository.PaymentRepository;
import nextstep.qna.exception.unchecked.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public EnrollmentService(
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository,
            PaymentRepository paymentRepository
    ) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.paymentRepository = paymentRepository;
    }


    @Transactional
    public void saveEnrollment(EnrollmentSaveRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(NotFoundException::new);
        if (course.isPaid()) {
            // TODO 결제 모듈 이벤트 처리로 리팩토링 예정.
            Payment payment = paymentRepository.findById(request.getPaymentId())
                    .orElseThrow(NotFoundException::new);
            if (!payment.isPayedCohort(request.getCohortId())) {
                throw new IllegalArgumentException("결제정보와 강의정보가 상이합니다.");
            }
        }

        Enrollment enrollment = new CourseDomainService().registerEnrollment(
                course, request.getCohortId(), request.getStudentId());

        enrollmentRepository.save(enrollment);
        courseRepository.plusOnePresentCount();
    }
}
