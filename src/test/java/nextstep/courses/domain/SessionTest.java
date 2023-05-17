package nextstep.courses.domain;

import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
  private Session session;

  @BeforeEach
  public void setUp() {
    session = new Session(1L, SessionPayment.FREE, 1);
    session.changeSessionStatus(SessionStatus.ACCEPTING);
    session.processEnrollment(NextStepUserTest.JAVAJIGI);
  }

  @Test
  @DisplayName("현재 수강 인원 확인")
  public void 수강_인원_확인() {
    assertThat(session.currentEnrollmentCount()).isEqualTo(1);
  }

  @Test
  @DisplayName("수강 신청 만석 시 IllegalArgumentException throw")
  public void 수강_신청_만석() {
    assertThatThrownBy(() -> session.processEnrollment(NextStepUserTest.SANJIGI))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }
}
