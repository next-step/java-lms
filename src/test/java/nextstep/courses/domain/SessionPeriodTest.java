package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

class SessionPeriodTest {

  public static final SessionPeriod TEST_SESSION_PERIOD = new SessionPeriod(
      LocalDateTime.of(2023, 5, 1, 10, 0),
      LocalDateTime.of(2023, 7, 1, 10, 0));
}