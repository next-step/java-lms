package nextstep.courses.domain;

import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
  private Session session;

  @BeforeEach
  public void setUp() {
    LocalDateTime currentTime = LocalDateTime.now();
    session = new Session(1L, SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime);
  }

  @Test
  @DisplayName("현재 수강 인원 확인")
  public void 수강_인원_확인() {
    session.processEnrollment(NextStepUserTest.JAVAJIGI);

    assertThat(session.currentEnrollmentCount()).isEqualTo(1);
  }

  @Test
  @DisplayName("수강 신청 만석 시 IllegalArgumentException throw")
  public void 수강_신청_만석() {
    session.processEnrollment(NextStepUserTest.JAVAJIGI);

    assertThatThrownBy(() -> session.processEnrollment(NextStepUserTest.SANJIGI))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }

  @Test
  @DisplayName("수강 신청이 모집중이 아닐 시 IllegalArgumentException throw")
  public void 준비중_시_신청() {
    session.ending();

    assertThatThrownBy(() -> session.processEnrollment(NextStepUserTest.JAVAJIGI))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : 종료");
  }
}
