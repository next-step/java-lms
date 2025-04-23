package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.factory.CourseFactory;
import nextstep.stub.factory.TestCourseFactory;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestCourseRepository;
import nextstep.stub.service.TestSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseServiceTest {

    @DisplayName("course 만들기")
    @Test
    void testCreateCourse() {
        TestCourseRepository courseRepository = new TestCourseRepository(1L, null);
        CourseService courseService = new CourseService(
            courseRepository,
            new CourseFactory(new TestSessionFactory()),
            new TestSessionService()
        );

        courseService.createCourse("test-title", 1L);

        assertThat(courseRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("course 삭제")
    @Test
    void testDeleteCourse() {
        LocalDateTime testLocalDateTime = LocalDateTime.now();
        Course course = new Course("1", "test-course", 3L, testLocalDateTime, testLocalDateTime);
        TestCourseRepository courseRepository = new TestCourseRepository(1L, null);
        TestCourseFactory courseFactory = new TestCourseFactory(course);
        TestSessionService sessionService = new TestSessionService();
        CourseService courseService = new CourseService(courseRepository, courseFactory, sessionService);

        courseService.deleteCourse(1L);

        assertAll(
            () -> assertThat(courseRepository.getDeleteCalled()).isEqualTo(1),
            () -> assertThat(sessionService.getDeleteSessionsCalled()).isEqualTo(1)
        );
    }
}
