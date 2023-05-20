package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void registerSession(long courseId, long sessionId) {
        final Course course = repository.findById(courseId);

        course.registerSession(sessionId, 1);

        repository.save(course);
    }
}
