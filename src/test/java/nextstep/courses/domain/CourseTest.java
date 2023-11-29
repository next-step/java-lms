package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {
    @Test
    @DisplayName("같은 title, 같은 creatorId를 가지더라도, 기수가 다르면 다른 과정이다. (Course는 기수 단위로 운영)")
    void testCourseDistinctByGeneration() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final long creatorId = 1L;
        Course course1 = new Course(title, 1, creatorId);
        Course course2 = new Course(title, 2, creatorId);

        //when
        final boolean isCourseEquals = course1.equals(course2);

        //then
        assertThat(isCourseEquals).isFalse();
    }

    @Test
    @DisplayName("같은 title, 같은 generation ,같은 creatorId를 가지면, 같은 과정이다.")
    void testCourseEqualsByTitleAndGenerationAndCreator() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final long creatorId = 1L;
        Course course1 = new Course(title, 1, creatorId);
        Course course2 = new Course(title, 1, creatorId);

        //when
        final boolean isCourseEquals = course1.equals(course2);

        //then
        assertThat(isCourseEquals).isTrue();
    }

    @Test
    @DisplayName("여러개의 강의(Session)을 가질 수 있다.")
    void testCourseHaveMultipleSessions() {
        //given
        Course course = new Course("TDD, 클린 코드 with Java", 1, 1L);

        //when
        course.addSession(new Session());
        course.addSession(new Session());
        course.addSession(new Session());

        //then
        assertThat(course.getSessions()).hasSize(3);
    }
}
