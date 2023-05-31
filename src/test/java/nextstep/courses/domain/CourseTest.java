package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CourseTest {

  private Course course;

  @BeforeEach
  public void setUp() {
    course = new Course();
  }

  @Test
  @DisplayName("과정에 여러 강의 추가 확인 테스트")
  public void 과정에_추가된_세션_확인() {
    LocalDateTime currentTime = LocalDateTime.now();
    course.addSession(new Session(1L, SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime));
    course.addSession(new Session(2L, SessionPayment.PAID, SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING, 2, currentTime, currentTime.plusDays(1), "https://twony.com", currentTime, currentTime));

    Assertions.assertThat(course.sessions()).hasSize(2);
  }
}
