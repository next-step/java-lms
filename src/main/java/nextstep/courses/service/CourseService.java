package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void registerSession(Long courseId, Long sessionId, NsUser nsUser) {
        Course course = courseRepository.findById(courseId);
        course.findSession(sessionId).register(nsUser);
    }
}
