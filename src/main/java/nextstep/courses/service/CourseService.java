package nextstep.courses.service;

import nextstep.courses.controller.EnrollRequestDto;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.PaidSession;
import nextstep.payments.service.PaymentService;
import nextstep.users.NsUserService;
import nextstep.users.domain.NsUser;

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
        NsUser nsUser = userService.findById(enrollRequestDto.getUserIdx());

        Course course = courseRepository.findById(enrollRequestDto.getCourseIdx());

        if (isFreeSession(enrollRequestDto, course)) {
            course.enroll(nsUser, enrollRequestDto.getSessionIdx());
        }
        payAndEnroll(enrollRequestDto, nsUser, course);
    }

    private void payAndEnroll(EnrollRequestDto enrollRequestDto, NsUser nsUser, Course course) {
        paymentService.pay(nsUser, (PaidSession) course.getSession(enrollRequestDto.getSessionIdx()));
        course.enroll(nsUser, enrollRequestDto.getSessionIdx());
    }

    private static boolean isFreeSession(EnrollRequestDto enrollRequestDto, Course course) {
        return course.isFreeSession(enrollRequestDto.getSessionIdx());
    }
}
