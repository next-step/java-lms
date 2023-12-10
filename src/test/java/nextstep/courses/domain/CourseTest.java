package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CourseTest {
    @Test
    @DisplayName("과정(Course)은 여러 개의 강의(Session)를 가질 수 있다")
    void add_session() {
        LocalDateTime now = LocalDateTime.now();
        FreeSession freeSession = new FreeSession(
                new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, now),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PREPARING,
                true,
                now
        );
        PaidSession paidSession = new PaidSession(
                new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, now),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PROGRESSING,
                true,
                800_000L,
                1
                , now
        );

        Course course = new Course("TDD, 클린 코드 with Java", 1L, now);
        course.addSession(freeSession);
        course.addSession(paidSession);

        Assertions.assertThat(course.sessions()).isEqualTo(Arrays.asList(freeSession, paidSession));


    }
}
