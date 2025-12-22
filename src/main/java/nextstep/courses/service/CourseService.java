package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Sessions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
    }

    public Long save(Course course) {
        Long courseId = courseRepository.save(course);

        if (course.getSessions() != null) {
            Sessions sessions = course.getSessions();
            for (int i = 1; i <= sessions.size(); i++) {
                Session session = sessions.findByCohort(i);
                if (session != null) {
                    sessionRepository.save(courseId, session);
                }
            }
        }

        return courseId;
    }

    public Course findById(Long courseId) {
        Course course = courseRepository.findById(courseId);
        Sessions sessions = sessionRepository.findByCourseId(courseId);
        return new Course(course.getId(), course.getTitle(), course.getCreatorId(),
                course.getCreatedAt(), null, sessions);
    }
}
