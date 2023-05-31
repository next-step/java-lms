package nextstep.courses.service;

import config.RollackableIntegrationTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.courses.domain.SessionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.AssertionUtils;

class SessionServiceTest extends RollackableIntegrationTest {

  @Autowired
  SessionService sut;

  NsUser 유저;

  @BeforeEach
  void setup() {
    유저 = NsUserTest.SIGHT;
  }

  @Test
  @DisplayName("존재하지 않는 강의는 수강 할 수 없다.")
  void 존재_하지_않는_강의_수강_불가() {
    Long 존재하지_않는_강의_번호 = 999999999L;
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 존재하지_않는_강의_번호),
        SessionExceptionCode.SESSION_NOT_FOUND
    );
  }

  @Test
  @DisplayName("수강중인 강의는 다시 수강신청 할 수 없다.")
  void 이미_수강한_강의는_다시_수강_불가() {
    // given
    Session 강의 = new Session(SessionTest.S4);
    sut.takeSession(유저, 강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 강의.getId()),
        SessionExceptionCode.STUDENT_ALREADY_REGISTERED
    );
  }

  @Test
  @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
  void 최대_수강_인원을_초과해서_등록_할_수_없음() {
    // given
    Session 최대_수강인원_1명인_강의 = new Session(SessionTest.S5);
    NsUser user = NsUserTest.SANJIGI;

    sut.takeSession(유저, 최대_수강인원_1명인_강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(user, 최대_수강인원_1명인_강의.getId()),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강사가 아니면 수강신청자를 승인 할 수 없다.")
  void 강사가_아닌_인원이_승인신청_안됨() {
    // given
    // S5의 강사는 = NsUserTest.JAVAJIGI;
    Session 강의 = new Session(SessionTest.S5);
    NsUser 강사가_아닌_유저 = NsUserTest.SANJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.approveSession(수강생, 강의.getId(), 강사가_아닌_유저),
        SessionExceptionCode.TEACHER_NOT_FOUND
    );
  }

  @Test
  @DisplayName("강의강사면 수강신청자를 승인 할 수 있다.")
  void 강의의_강사는_승인_가능() {
    // given
    Session 강의 = new Session(SessionTest.S5);
    NsUser 강사 = NsUserTest.JAVAJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());

    // when
    int 승인된_수강생_수 = sut.approveSession(수강생, 강의.getId(), 강사);

    // then
    Assertions.assertThat(승인된_수강생_수).isEqualTo(1);
  }

  @Test
  @DisplayName("강의강사면 수강신청자를 승인 거절 할 수 있다.")
  void 강의의_강사는_승인_거절_가능() {
    // given
    Session 강의 = new Session(SessionTest.S5);
    NsUser 강사 = NsUserTest.JAVAJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());

    // when
    int 승인된_수강생_수 = sut.refuseSession(수강생, 강의.getId(), 강사);

    // then
    Assertions.assertThat(승인된_수강생_수).isEqualTo(1);
  }

  @Test
  @DisplayName("강의를 취소한 인원은 강사가 승인 할 수 없다.")
  void 강의를_스스로_취소한_인원은_승인_불가() {
    // given
    Session 강의 = new Session(SessionTest.S5);
    NsUser 강사 = NsUserTest.JAVAJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());
    sut.cancelSession(수강생, 강의.getId());

    // when
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.approveSession(수강생, 강의.getId(), 강사),
        SessionExceptionCode.STUDENT_NOT_FOUND
    );
  }

  @Test
  @DisplayName("강의를 취소한 인원은 강사가 승인 할 수 없다.")
  void 강의를_스스로_취소한_인원은_승인_거절_불가() {
    // given
    Session 강의 = new Session(SessionTest.S5);
    NsUser 강사 = NsUserTest.JAVAJIGI;
    NsUser 수강생 = NsUserTest.SIGHT;

    sut.takeSession(수강생, 강의.getId());
    sut.cancelSession(수강생, 강의.getId());

    // when
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.refuseSession(수강생, 강의.getId(), 강사),
        SessionExceptionCode.STUDENT_NOT_FOUND
    );
  }
}
