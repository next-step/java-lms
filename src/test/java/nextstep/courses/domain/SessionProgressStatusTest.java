package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SessionProgressStatusTest {

  private SessionProgressStatus preparingStatus;
  private SessionProgressStatus acceptingStatus;
  private SessionProgressStatus endingStatus;

  @BeforeEach
  public void setUp() {
    preparingStatus = SessionProgressStatus.PREPARING;
    acceptingStatus = SessionProgressStatus.ACCEPTING;
    endingStatus = SessionProgressStatus.ENDING;
  }

  @Test
  @DisplayName("수강 신청 상태가 아니면 IllegalArgumentException throw")
  public void 수강_신청_아닌_상태() {
    assertAll(
            () -> assertThat(preparingStatus.canEnroll()).isFalse(),
            () -> assertThat(acceptingStatus.canEnroll()).isTrue(),
            () -> assertThat(endingStatus.canEnroll()).isFalse()
    );
  }
}
