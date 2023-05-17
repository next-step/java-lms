package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionsTest {

  private Sessions sessions;

  @BeforeEach
  public void setUp() {
    sessions = new Sessions();
  }

  @Test
  @DisplayName("세션 추가 후 세션 카운트 확인")
  public void 세션_카운트_확인() {
    LocalDateTime currentTime = LocalDateTime.now();
    sessions.addSession(new Session(1L, SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com"));
    sessions.addSession(new Session(2L, SessionPayment.PAID, SessionStatus.PREPARING, 2, currentTime, currentTime.plusDays(1), "https://twony.com"));

    assertThat(sessions.currentSessionCount()).isEqualTo(2);
  }
}
