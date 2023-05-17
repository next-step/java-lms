package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionDateTest {

  private SessionDate startDate;
  private SessionDate closedDate;

  @BeforeEach
  public void setUp() {
    startDate = new SessionDate(LocalDateTime.of(2023, 5, 17, 0, 0, 0));
    closedDate = new SessionDate(LocalDateTime.of(2023, 5, 15, 0, 0, 0));
  }

  @Test
  @DisplayName("종료일이 시작일 이후인 경우 IllegalArgumentException throw")
  public void 종료일_확인() {
    Assertions.assertThatThrownBy(() -> startDate.validateClosedDate(closedDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("종료일은 시작일 이후여야 합니다. 현재 시작일 : 2023-05-17, 현재 종료일 : 2023-05-15");

  }
}
