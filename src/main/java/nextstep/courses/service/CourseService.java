package nextstep.courses.service;

import nextstep.courses.CannotRegisterException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class CourseService {

    private CourseRepository courseRepository;

    public void register(Long CourseId, Long sessionId, NsUser user) throws CannotRegisterException {
        Course course = courseRepository.findById(CourseId);
        course.register(sessionId, user);
    }

    public void register(Long CourseId, Long sessionId, NsUser user, Payment payment) throws CannotRegisterException {
        Course course = courseRepository.findById(CourseId);
        course.register(sessionId, user, payment);
    }
}
