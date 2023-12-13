package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseTest {
    @Test
    @DisplayName("과정(Course)은 여러 개의 강의(Session)를 가질 수 있다")
    void add_session() {
        LocalDateTime now = LocalDateTime.now();

        CoverImage coverImage1 = new CoverImage("images/test.gif", 1000_000, "gif", 300, 200, now);
        CoverImage coverImage2 = new CoverImage("images/test.jpeg", 1000_000, "jpeg", 300, 200, now);
        List<CoverImage> coverImages = new ArrayList<>(Arrays.asList(coverImage1, coverImage2));


        FreeSession freeSession = new FreeSession(
                coverImages,
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionProgressState.PREPARING,
                true,
                now
        );
        PaidSession paidSession = new PaidSession(
                coverImages,
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
