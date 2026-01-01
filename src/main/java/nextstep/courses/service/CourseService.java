package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
    }

    public Course getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId);
        Sessions sessions = new Sessions(sessionRepository.findByCourseId(courseId));
        course.attachSessions(sessions);
        return course;
    }
}
