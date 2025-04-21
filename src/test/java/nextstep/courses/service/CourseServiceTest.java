package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.factory.CourseFactory;
import nextstep.stub.factory.TestCourseFactory;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestCourseRepository;
import nextstep.stub.repository.TestSessionImageRepository;
import nextstep.stub.repository.TestSessionRepository;
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
        TestCourseRepository courseRepository = new TestCourseRepository(1L, null);
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        CourseService courseService = new CourseService(
            courseRepository,
            new TestSessionRepository(1L, null, List.of()),
            sessionImageRepository,
            new CourseFactory(new TestSessionFactory())
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
        TestCourseFactory courseFactory = new TestCourseFactory(course);
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
