package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class SessionPeriodTest {

  public static final SessionPeriod TEST_SESSION_PERIOD = new SessionPeriod(
      LocalDateTime.of(2023, 5, 1, 10, 0),
      LocalDateTime.of(2023, 7, 1, 10, 0));


  @Test
  void 강의_시작일이_강의_종료일보다_늦을_수_없다() {
    assertThatThrownBy(() -> new SessionPeriod(
        LocalDateTime.of(2023, 7, 1, 10, 0),
        LocalDateTime.of(2023, 5, 1, 10, 0)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("시작 시간이 종료 시간보다 늦을 수 없습니다.");
  }

  @Test
  void 강의_시작일이_강의_종료일보다_이전인_경우_정상_테스트() {
    assertThatNoException().isThrownBy(() -> new SessionPeriod(
        LocalDateTime.of(2023, 5, 1, 10, 0),
        LocalDateTime.of(2023, 7, 1, 10, 0)));
  }

}