package nextstep.courses.domain.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public void registerFreeSession(Long courseId, Session session, NsUser user) {
        Course selectedCourse = courseRepository.findById(courseId);

        session.registerForFreeSession(selectedCourse);
        courseRepository.save(selectedCourse);
    }

    public void registerPaidSession(Long courseId, Session session, NsUser user, Payment payment) {
        Course selectedCourse = courseRepository.findById(courseId);

        session.registerForPaidSession(selectedCourse, payment);
        courseRepository.save(selectedCourse);
    }
}
