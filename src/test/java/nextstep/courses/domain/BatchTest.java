package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.DuplicatedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BatchTest {

  public final Course C1 = new Course("ssafy", 1L);
  public final Session S1 = new Session("tdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 1);

  @DisplayName("기수(Batch)는 중복되는 강의(Session)를 추가할 수 없다.")
  @Test
  public void addSession_throwException_ifDuplicatedSessionAdd() {
    Batch batch = C1.createdBatch(1L);
    batch.addSession(S1);

    assertThatThrownBy(() -> batch.addSession(S1)).isInstanceOf(DuplicatedException.class);
  }
}
