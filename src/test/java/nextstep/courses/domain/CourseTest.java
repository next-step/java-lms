package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

public class CourseTest {
    public static Course c1 = new Course(1L, "clean code", 1L
            , LocalDateTime.of(2023, 5, 6, 12, 0, 0)
            , LocalDateTime.of(2023, 12, 6, 12, 0, 0));

    @Test
    @DisplayName("강의 등록")
    void addSession() {
        c1.addSession(SessionTest.s1);
        Sessions sessions = new Sessions();
        sessions.addSession(SessionTest.s1);

        assertThat(c1.getSessions()).isEqualTo(sessions);
    }
}
