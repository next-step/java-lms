package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionStatusTest {

  private SessionStatus preparingStatus;

  @BeforeEach
  public void setUp() {
    preparingStatus = SessionStatus.PREPARING;
  }

  @Test
  @DisplayName("수강 신청 상태가 아니면 IllegalArgumentException throw")
  public void 수강_신청_아닌_상태() {
    assertThatThrownBy(() -> preparingStatus.validateAcceptingStatus())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : 준비중");
  }
}
