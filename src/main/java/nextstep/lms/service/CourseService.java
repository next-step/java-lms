package nextstep.lms.service;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service("courseService")
public class CourseService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public Optional<Course> findById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
