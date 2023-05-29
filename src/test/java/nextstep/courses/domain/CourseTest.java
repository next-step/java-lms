package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CourseTest {

    @Test
    @DisplayName("한개의 코스는 여러가지 세션을 가질 수 있다.")
    public void addSessions() {
        Course course = new Course("자바", 1L);
        course.addSession(new Session(1L,
                1L,
                new SessionInfo("자바1기", 1L, "./image"),
                LocalDateTime.of(2023, 5, 1, 0, 0, 0),
                LocalDateTime.of(2023, 6, 30, 23, 59, 59),
                new Price(false, 100_000),
                Status.WAITING,
                new Students(10)));
        Assertions.assertThat(course.manySessions()).isEqualTo(1);
    }
}
