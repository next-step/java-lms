package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

  @DisplayName("강의는 종료일은 시작일보다 이전일 수 없다.")
  @Test
  public void throwException_ifEndDateBeforeStartDate() {
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.minusDays(3);
    assertThatThrownBy(() -> new Session("title", start, end, "img", SessionType.FREE, 10))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("강의는 최대 수강생 수는 1보다 작을 수 없다.")
  @Test
  public void throwException_ifLess0MaxRecruitment() {
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plusDays(3);
    assertThatThrownBy(() -> new Session("title", start, end, "img", SessionType.FREE, 0))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("강의를 생성한다.")
  @Test
  public void session() {
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plusDays(3);
    Session session = new Session("title", start, end, "img", SessionType.FREE, 100);
  }

}
