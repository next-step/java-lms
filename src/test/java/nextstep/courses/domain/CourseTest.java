package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class CourseTest {
    @Test
    @DisplayName("과정(Course)은 여러 개의 강의(Session)를 가질 수 있다")
    void add_session() {
        FreeSession freeSession = new FreeSession(
                1L,
                new CoverImage(1000_000, "gif", 300, 200),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionState.PREPARING
        );
        PaidSession paidSession = new PaidSession(
                2L,
                new CoverImage(1000_000, "gif", 300, 200),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionState.RECRUITING,
                800_000L,
                1
        );

        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        course.addSession(freeSession);
        course.addSession(paidSession);

        Assertions.assertThat(course.sessions()).isEqualTo(Arrays.asList(freeSession, paidSession));


    }
}
