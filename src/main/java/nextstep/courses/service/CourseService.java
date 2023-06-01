package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }
}
