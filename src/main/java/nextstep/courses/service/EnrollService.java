package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Sessions;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("enrollService")
public class EnrollService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional
    public void enrollCourse(long courseId, long sessionId, Payment payment) {
        Course course = courseRepository.findById(courseId);
        Sessions sessions = course.getSessions();
        Session session = sessions.findById(sessionId);
        session.enroll(payment);
    }
}
