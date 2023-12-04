package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.repository.CourseRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public void createCourse(String title, NsUser nsUser) {
        Course course = new Course(title, nsUser.getId());
        courseRepository.save(course);
    }
}
