package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.stub.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseServiceTest {

    @DisplayName("course 만들기")
    @Test
    void testCreateCourse() {
        Course course = new Course("1", "test-course", 3L, LocalDateTime.now(), LocalDateTime.now());

        TestCourseRepository courseRepository = new TestCourseRepository(1L, null);
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        CourseService courseService = new CourseService(
            courseRepository,
            new TestSessionRepository(1L, null, List.of()),
            sessionImageRepository,
            new TestCourseFactory(new TestSessionsFactory(), course)
        );

        courseService.createCourse("test-title", 1L);

        assertThat(courseRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("course 삭제")
    @Test
    void testDeleteCourse() throws IOException {
        LocalDateTime testLocalDateTime = LocalDateTime.now();
        Course course = new Course("1", "test-course", 3L, testLocalDateTime, testLocalDateTime);

        TestCourseRepository courseRepository = new TestCourseRepository(1L, null);
        TestCourseFactory courseFactory = new TestCourseFactory(new TestSessionsFactory(), course);
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository(List.of());

        CourseService courseService = new CourseService(
            courseRepository,
            new TestSessionRepository(1L, null, List.of()),
            sessionImageRepository,
            courseFactory
        );

        courseService.deleteCourse(1L);

        assertAll(
            () -> assertThat(courseFactory.getCreateCalled()).isEqualTo(1),
            () -> assertThat(course.isDeleted()).isTrue()
        );
    }
}
