package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BatchTest {

  public static final Course C1 = new Course("ssafy", 1L);
  public static final Batch B1 = C1.createdBatch(1L);
  public static final Session S1 = new Session("tdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 80);
  public static final Session S2 = new Session("atdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(1), "atdd-img", SessionType.PAID, 30);

  @DisplayName("기수(Batch)는 중복되는 강의(Session)를 추가할 수 없다.")
  @Test
  public void addSession_throwException_ifDuplicatedSessionAdd() {
    B1.addSession(S1);

    assertThatThrownBy(() -> B1.addSession(S1)).isInstanceOf(IllegalArgumentException.class);
  }
}
