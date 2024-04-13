package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.JdbcCourseRepository;

public class CourseService {

    JdbcCourseRepository courseRepository;

    public void enroll(Long courseId, Long userId, Long sessionId) {
        // 강의에 수강생을 추가하는 비즈니스 로직
        Course course = courseRepository.findById(courseId);
        course.enroll(userId, sessionId);
    }
}
