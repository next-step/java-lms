package nextstep.courses.service;

import config.RollackableIntegrationTest;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.teacher.SessionTeacher;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import utils.AssertionUtils;

@Deprecated
public class SessionStudentServiceLegacyTest extends RollackableIntegrationTest {

  @Autowired
  SessionStudentService sut;

  @Test
  @DisplayName("Step 4 이전에 추가된 강의에 대해서는 강사가 수강신청을 승인 할 수 없다. (이미 승인처리라고 보기 때문)")
  void 레거시_강의는_수강_승인_불가() {
    // given
    NsUser 수강생_유저 = NsUserTest.SIGHT;
    Session 레거시_강의 = SessionTest.S1;
    SessionTeacher 강사 = new SessionTeacher(레거시_강의, NsUserTest.JAVAJIGI);
    SessionStudent 수강생 = new SessionStudent(레거시_강의, 수강생_유저.getId());

    레거시_강의.addPersonnel(수강생_유저);

    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.approveSession(레거시_강의, 강사, 수강생),
        SessionExceptionCode.UNSUPPORTED_OPERATION
    );
  }

  @Test
  @DisplayName("Step 4 이전에 추가된 강의에 대해서는 강사가 수강신청을 승인거절 할 수 없다.")
  void 레거시_강의는_수강_취소_불가() {
    // given
    NsUser 수강생_유저 = NsUserTest.SIGHT;
    Session 레거시_강의 = new Session(SessionTest.S1);
    SessionTeacher 강사 = new SessionTeacher(레거시_강의, NsUserTest.JAVAJIGI);
    SessionStudent 수강생 = new SessionStudent(레거시_강의, 수강생_유저.getId());

    레거시_강의.addPersonnel(수강생_유저);

    AssertionUtils.assertThatThrowsLmsException(
        () -> sut.refuseSession(레거시_강의, 강사, 수강생),
        SessionExceptionCode.UNSUPPORTED_OPERATION
    );
  }
}
