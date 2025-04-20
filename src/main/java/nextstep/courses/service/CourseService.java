package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.factory.CourseFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;
    private final CourseFactory courseFactory;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository, CourseFactory courseFactory) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.courseFactory = courseFactory;
    }

    public void createCourse(String title, Long creatorId) {
        Course course = new Course(title, creatorId);
        courseRepository.save(course.toCourseEntity());
    }

    @Transactional
    public void deleteCourse(long courseId) throws IOException {
        Course course = courseFactory.create(courseRepository.findById(courseId), sessionRepository.findAllByCourseId(courseId));
        course.delete();
    }
}
