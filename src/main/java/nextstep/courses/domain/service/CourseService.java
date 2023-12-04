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

    public void registerSession(Long courseId, Session session, NsUser user, Payment payment) {
        Course selectedCourse = courseRepository.findById(courseId);
        if (session.isOpen() && session.isFree()) {
            addSessionToCourse(session, selectedCourse);
        }
        if (session.isOpen() && !session.isFree() && session.isPaymentCorrect(payment)) {
            addSessionToCourse(session, selectedCourse);
        }
    }

    private void addSessionToCourse(Session session, Course selectedCourse) {
        session.register(selectedCourse);
        courseRepository.save(selectedCourse);
    }
}
