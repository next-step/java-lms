package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CourseTest {

    @Test
    @DisplayName("과정은 기수 값을 가지고 있다.")
    void courseHasGenerationTest() {
        var course = new Course(1, new Sessions());
        Assertions.assertThat(course.getGeneration().getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("과정은 여러 개의 강의를 가질 수 있다.")
    void courseHasLecturesTest() {
        var session1 = new TestSession();
        var session2 = new TestSession();
        var course = new Course(1, new Sessions(session1, session2));

        Assertions.assertThat(course.getSessions().hasSession(session1)).isTrue();
        Assertions.assertThat(course.getSessions().hasSession(session2)).isTrue();
    }

    static class TestSession extends Session {

        protected TestSession() {
            super(LocalDate.MIN, LocalDate.MAX, CoverImage.defaultImage());
        }
    }
}
