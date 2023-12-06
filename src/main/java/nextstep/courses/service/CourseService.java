package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.users.domain.NsUser;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(String title, NsUser nsUser) {
        Course course = new Course(title, nsUser.getId());
        courseRepository.save(course);
    }
}
