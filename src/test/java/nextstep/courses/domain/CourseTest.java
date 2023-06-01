package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CourseTest {
    @Test
    @DisplayName("과정은 여러개의 강의를 가질 수 있다.")
    void 과정_강의_일대다관계() {
        Course course = new Course(1L, "Course1", 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(SessionBuilder.aSession().build());
        course.addSession(SessionBuilder.aSession().build());

        Assertions.assertThat(course.getSessions()).hasSize(2);
    }
}
