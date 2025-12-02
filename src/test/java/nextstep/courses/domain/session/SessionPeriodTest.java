package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionPeriodTest {

  @Test
  void 시작일이_종료일보다_이전이면_정상_생성() {
    assertThatCode(() -> new SessionPeriod("2024-01-01", "2024-01-31"))
        .doesNotThrowAnyException();
  }

  @Test
  void 시작일과_종료일이_같으면_정상_생성() {
    assertThatCode(() -> new SessionPeriod("2024-01-01", "2024-01-01"))
        .doesNotThrowAnyException();
  }

  @Test
  void 시작일이_종료일보다_이후면_예외() {
    assertThatThrownBy(() -> new SessionPeriod("2024-01-31", "2024-01-01"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("시작일은 종료일보다 이전이어야 합니다.");
  }

  @Test
  void LocalDate로_생성() {
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 1, 31);

    assertThatCode(() -> new SessionPeriod(start, end))
        .doesNotThrowAnyException();
  }
}