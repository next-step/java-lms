package nextstep.qna.service;

import config.RollackableIntegrationTest;
import java.util.ArrayList;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionStudents;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.courses.service.SessionService;
import nextstep.qna.domain.SessionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.AssertionUtils;

class SessionServiceTest extends RollackableIntegrationTest {

  @Autowired
  SessionService sut;

  NsUser 유저;

  Session 강의_준비중;
  Session 강의_모집중;

  @BeforeEach
  void setup() {
    유저 = NsUserTest.SIGHT;
    강의_준비중 = new Session(SessionTest.S2, getEmptyStudents());
    강의_모집중 = new Session(SessionTest.S1, getEmptyStudents());
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
    Session 강의_모집중_정원_3명 = new Session(SessionTest.S3, getEmptyStudents());
    sut.takeSession(유저, 강의_모집중_정원_3명.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 강의_모집중_정원_3명.getId()),
        SessionExceptionCode.STUDENT_ALREADY_REGISTERED
    );
  }

  /**
   * TODO: SessionStatus.RECRUITING 마이그레이션 이후 수정 필요
   * @see SessionProgressStatus
   */
  @Test
  @DisplayName("모집중 상태가 아닌 강의는 수강 할 수 없다")
  void 모집중인_강의만_수강_가능() {
    // given

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(유저, 강의_준비중.getId()),
        SessionExceptionCode.CANNOT_ENROLL_SESSION
    );
  }

  /**
   * TODO: SessionStatus.RECRUITING 마이그레이션 이후 수정 필요
   * @see SessionProgressStatus
   */
  @Test
  @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
  void 최대_수강_인원을_초과해서_등록_할_수_없음() {
    // given
    Session 최대_수강인원_1명인_강의 = new Session(SessionTest.S1, getEmptyStudents());
    NsUser user = NsUserTest.SANJIGI;

    sut.takeSession(유저, 최대_수강인원_1명인_강의.getId());

    // when && then
    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.takeSession(user, 최대_수강인원_1명인_강의.getId()),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  private SessionStudents getEmptyStudents() {
    return new SessionStudents(new ArrayList<>());
  }
}
