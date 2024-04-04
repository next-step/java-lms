package nextstep.courses.service;

import nextstep.courses.controller.EnrollRequestDto;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.payments.service.PaymentService;
import nextstep.users.NsUserService;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import nextstep.users.domain.UserRepository;

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

        //TODO
    }
}
