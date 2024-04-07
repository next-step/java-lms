package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseTest {
  @Test
  void 과정_생성() {
    LocalDateTime now = LocalDateTime.now();
    Course course = Course.of(
            1L,
            1L,
            "title1",
            List.of(
                    new FreeSession (1L, LocalDate.now(), LocalDate.now().plusMonths(1L), new File("test"), SessionStatus.OPEN, List.of()),
                    new FreeSession(2L, LocalDate.now(), LocalDate.now().plusMonths(1L), new File("test"), SessionStatus.OPEN, List.of()),
                    new FreeSession(3L, LocalDate.now(), LocalDate.now().plusMonths(1L), new File("test"), SessionStatus.OPEN, List.of())
            ),
            1L,
            now,
            now
    );
  }
}
