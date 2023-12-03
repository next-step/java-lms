package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }
}
