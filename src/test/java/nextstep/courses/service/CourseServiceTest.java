package nextstep.courses.service;

import nextstep.courses.factory.CourseFactory;
import nextstep.stub.repository.TestCourseRepository;
import nextstep.stub.service.TestSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseServiceTest {

    @DisplayName("course 만들기")
    @Test
    void testCreateCourse() {
        TestCourseRepository courseRepository = new TestCourseRepository(1L);
        CourseService courseService = new CourseService(
            courseRepository,
            new CourseFactory(),
            new TestSessionService()
        );

        courseService.createCourse("test-title", 1L);

        assertThat(courseRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("course 삭제")
    @Test
    void testDeleteCourse() {
        TestCourseRepository courseRepository = new TestCourseRepository(1L);
        TestSessionService sessionService = new TestSessionService();
        CourseService courseService = new CourseService(courseRepository, new CourseFactory(), sessionService);

        courseService.deleteCourse(1L);

        assertAll(
            () -> assertThat(courseRepository.getDeleteCalled()).isEqualTo(1),
            () -> assertThat(sessionService.getDeleteSessionsCalled()).isEqualTo(1)
        );
    }
}
