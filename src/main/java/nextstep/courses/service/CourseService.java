package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional
    public void makeCourse(String title, NsUser loginUser, int ordinal) {
        Course course = new Course(title, loginUser.getId(), ordinal);
        courseRepository.save(course);
    }
}
