package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.JdbcCourseRepository;

public class CourseService {

    private final JdbcCourseRepository jdbcCourseRepository;

    public CourseService(JdbcCourseRepository jdbcCourseRepository) {
        this.jdbcCourseRepository = jdbcCourseRepository;
    }

    public Course findByCardinalNumber(int cardinalNumber) {
        return jdbcCourseRepository.findByCardinalNumber(cardinalNumber);
    }
}
