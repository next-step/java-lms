package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.SessionPeriod.INVALID_SESSION_PERIOD;
import static nextstep.courses.domain.session.SessionPeriod.INVALID_SESSION_STARTED_AT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {

  @Test
  @DisplayName("정상 시작일과 종료일을 넣은 경우" +
      "SessionPeriod 생성 테스트")
  void sessionPeriodTest() {
    LocalDateTime startedAt = LocalDateTime.now();
    LocalDateTime endedAt = LocalDateTime.now().plusDays(10);

    SessionPeriod sessionPeriod = new SessionPeriod(startedAt, endedAt);
    assertThat(sessionPeriod.isCorrectPeriod()).isTrue();
  }

  @Test
  @DisplayName("시작일이 null인 경우" +
      "exception 테스트")
  void sessionPeriodTest2() {
    LocalDateTime endedAt = LocalDateTime.now();
    assertThatThrownBy(() -> new SessionPeriod(null, endedAt))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_SESSION_PERIOD, null, endedAt));
  }

  @Test
  @DisplayName("종료일이 null인 경우" +
      "exception 테스트")
  void sessionPeriodTest3() {
    LocalDateTime startedAt = LocalDateTime.now();
    assertThatThrownBy(() -> new SessionPeriod(startedAt, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_SESSION_PERIOD, startedAt, null));
  }

  @Test
  @DisplayName("강의 시작일과 종료일이 같은 경우" +
      "exception 테스트")
  void sessionPeriodTest4() {
    LocalDateTime given = LocalDateTime.now();
    assertThatThrownBy(() -> new SessionPeriod(given, given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_SESSION_STARTED_AT, given, given));
  }

  @Test
  @DisplayName("강의 시작일이 종료일 보다 이후인 경우" +
      "exception 테스트")
  void sessionPeriodTest5() {
    LocalDateTime startedAt = LocalDateTime.now().plusDays(10);
    LocalDateTime endedAt = LocalDateTime.now();
    assertThatThrownBy(() -> new SessionPeriod(startedAt, endedAt))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_SESSION_STARTED_AT, startedAt, endedAt));
  }
}
