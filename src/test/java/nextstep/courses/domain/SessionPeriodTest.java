package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {

  @Test
  @DisplayName("종료일이 시작일 이후인 경우 IllegalArgumentException throw")
  public void 종료일_확인() {
    assertThatThrownBy(() -> new SessionPeriod(
            LocalDateTime.of(2023, 5, 17, 0, 0, 0),
            LocalDateTime.of(2023, 5, 15, 0, 0, 0)
    ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("종료일은 시작일 이후여야 합니다. 현재 시작일 : 2023-05-17, 현재 종료일 : 2023-05-15");
  }
}
