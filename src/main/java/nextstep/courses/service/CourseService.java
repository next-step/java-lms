package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public long save(Course course) {
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public Course findById(long id) {
        return courseRepository.findById(id);
    }
}
