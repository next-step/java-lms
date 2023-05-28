package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.DuplicatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BatchTest {

  private Course c1;
  private Session s1;

  @BeforeEach
  public void createTestData() {
    c1 = new Course("ssafy", 1L);
    s1 = new Session("tdd", LocalDateTime.now(),
        LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 1);
  }

  @DisplayName("기수(Batch)는 중복되는 강의(Session)를 추가할 수 없다.")
  @Test
  public void addSession_throwException_ifDuplicatedSessionAdd() {
    Batch batch = c1.createdBatch(1L);
    batch.addSession(s1);

    assertThatThrownBy(() -> batch.addSession(s1)).isInstanceOf(DuplicatedException.class);
  }
}
