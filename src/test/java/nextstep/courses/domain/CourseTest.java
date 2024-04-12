package nextstep.courses.domain;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseTest {
  public static SessionImage IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE", 1L);

  @BeforeEach
  void beforeEach() {
    IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE", 1L);
  }

  @Test
  void 과정_생성() {
    LocalDateTime now = LocalDateTime.now();
    Course course = Course.of(
            1L,
            "TEST-1기",
            "title1",
            List.of(
                    new FreeSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.OPEN, List.of()),
                    new FreeSession(2L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.OPEN, List.of()),
                    new FreeSession(3L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(IMAGE), SessionStatus.OPEN, List.of())
            ),
            1L,
            now,
            now
    );
  }
}
