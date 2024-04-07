package nextstep.courses.service;

import nextstep.courses.controller.EnrollRequestDto;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.session.PaidSession;
import nextstep.payments.service.PaymentService;
import nextstep.users.NsUserService;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CourseService {
    private final PaymentService paymentService;
    private final NsUserService userService;
    private final CourseRepository courseRepository;

    public CourseService(PaymentService paymentService, NsUserService userService, CourseRepository courseRepository) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.courseRepository = courseRepository;
    }

    public void enroll(EnrollRequestDto enrollRequestDto) {
        LocalDate requestTime = LocalDate.now();
        NsUser nsUser = userService.findById(enrollRequestDto.getUserIdx());

        Course course = courseRepository.findById(enrollRequestDto.getCourseIdx());

        if (isFreeSession(enrollRequestDto, course)) {
            course.enroll(nsUser, enrollRequestDto.getSessionIdx(), requestTime);
        }
        payAndEnroll(enrollRequestDto, nsUser, course, requestTime);
    }

    private void payAndEnroll(EnrollRequestDto enrollRequestDto, NsUser nsUser, Course course, LocalDate requestTime) {
        paymentService.pay(course.toPayment(nsUser, enrollRequestDto.getSessionIdx()));
        course.enroll(nsUser, enrollRequestDto.getSessionIdx(), requestTime);
    }

    private boolean isFreeSession(EnrollRequestDto enrollRequestDto, Course course) {
        return course.isFreeSession(enrollRequestDto.getSessionIdx());
    }
}
