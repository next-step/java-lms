package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("enrollService")
public class EnrollService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public String enrollCourse(NsUser loginUser, long courseId) {
        Course course = courseRepository.findById(courseId);
        return course.registerCourse(loginUser);
    }
}

