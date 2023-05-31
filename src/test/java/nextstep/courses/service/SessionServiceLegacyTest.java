package nextstep.courses.service;

import config.RollackableIntegrationTest;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.AssertionUtils;

@Deprecated
public class SessionServiceLegacyTest extends RollackableIntegrationTest {
  @Autowired
  SessionService sut;

  NsUser 유저;

  @BeforeEach
  void setup() {
    유저 = NsUserTest.SIGHT;
  }

  @Test
  @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다. (레거시)")
  @Deprecated
  void 최대_수강_인원을_초과해서_등록_할_수_없음_LEGACY() {
    // given
    Session 최대_수강인원_1명인_강의 = new Session(SessionTest.S1);
    NsUser user = NsUserTest.SANJIGI;

    sut.takeSession(유저, 최대_수강인원_1명인_강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(user, 최대_수강인원_1명인_강의.getId()),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다. (레거시)")
  @Deprecated
  void 최대_수강_인원을_초과해서_등록_할_수_없음_레거시() {
    // given
    Session 최대_수강인원_1명인_강의 = new Session(SessionTest.S1);
    NsUser user = NsUserTest.SANJIGI;

    sut.takeSession(유저, 최대_수강인원_1명인_강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(user, 최대_수강인원_1명인_강의.getId()),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("수강중인 강의는 다시 수강신청 할 수 없다. (레거시)")
  @Deprecated
  void 이미_수강한_강의는_다시_수강_불가() {
    // given
    Session 강의_모집중_정원_3명 = new Session(SessionTest.S3);
    sut.takeSession(유저, 강의_모집중_정원_3명.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 강의_모집중_정원_3명.getId()),
        SessionExceptionCode.STUDENT_ALREADY_REGISTERED
    );
  }

  /**
   * @see SessionProgressStatus.RECRUITING
   */
  @Test
  @DisplayName("모집중 상태가 아닌 강의는 수강 할 수 없다 (레거시)")
  @Deprecated
  void 모집중인_강의만_수강_가능() {
    // given
    // 신규로 추가된 모집중 상태가 null 이고, 기존의 모집중 상태가 아닌 경우
    Session 강의_준비중_LEGACY = new Session(SessionTest.S2);

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 강의_준비중_LEGACY.getId()),
        SessionExceptionCode.CANNOT_ENROLL_SESSION
    );
  }

  @Test
  @DisplayName("레거시인 강의는 승인/승인거절을 할 수 없다.")
  @Deprecated
  void 레거시_강의는_승인_또는_거절_불가() {
    // given
    Session 강의 = new Session(SessionTest.S1);
    NsUser 강사 = NsUserTest.JAVAJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());

    // when
    int 승인된_수강생_수 = sut.refuseSession(수강생, 강의.getId(), 강사);

    // then
    Assertions.assertThat(승인된_수강생_수).isEqualTo(0);
  }
}
