package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 모집중인 상태에서는 수강 신청 가능
 * 모집 중이라도 정원이 다 차면 수강 신청 불가능
 * 종료된 상태에서는 수강 신청 불가능
 */
class SessionTest {

  @Test
  void Session이_준비중_상태일때_수강신청자가_없으면_Session_생성_성공_테스트() {
    assertThatNoException().isThrownBy(() ->
        new Session(
            1L,
            SessionInfoTest.TEST_SESSION_INFO,
            ImageTest.TEST_IMAGE,
            SessionType.FREE,
            SessionStatus.PREPARING,
            EnrolledUsersTest.TEST_ENROLLED_NO_USERS,
            SessionPeriodTest.TEST_SESSION_PERIOD
        )
    );
  }

  @Test
  void Session이_준비중_상태일때_수강신청자가_있으면_Session_생성_성공_테스트() {
    assertThatThrownBy(() ->
        new Session(
            1L,
            SessionInfoTest.TEST_SESSION_INFO,
            ImageTest.TEST_IMAGE,
            SessionType.FREE,
            SessionStatus.PREPARING,
            EnrolledUsersTest.TEST_ENROLLED_USERS,
            SessionPeriodTest.TEST_SESSION_PERIOD
        )
    ).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("준비중 상태일때는 수강생이 없어야 합니다.");
  }



}