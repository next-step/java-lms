package nextstep.courses.service;

import config.RollackableIntegrationTest;
import nextstep.courses.domain.SessionTeacherTest;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.teacher.SessionTeacher;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionStudentServiceTest extends RollackableIntegrationTest {

  @Autowired
  SessionStudentService sut;

  @Autowired
  SessionService sessionService;

  @Test
  @DisplayName("Step 4 이후에 추가된 강의에 대해서는 강사가 수강신청을 승인 할 수 있다.")
  void 레거시_강의는_수강_승인_불가() {
    // given
    NsUser 수강생_유저 = NsUserTest.SIGHT;
    Session 강의 = new Session(SessionTest.S5);
    SessionTeacher 강사 = SessionTeacherTest.T5;
    SessionStudent 수강생 = 수강신청(강의, 수강생_유저);

    int changedStatusCount = sut.approveSession(강의, 강사, 수강생);

    Assertions.assertThat(changedStatusCount)
        .isEqualTo(1);
  }

  @Test
  @DisplayName("Step 4 이후에 추가된 강의에 대해서는 강사가 수강신청을 승인거절 할 수 있다.")
  void 레거시_강의는_수강_취소_불가() {
    // given
    NsUser 수강생_유저 = NsUserTest.SIGHT;
    Session 강의 = new Session(SessionTest.S5);
    SessionTeacher 강사 = SessionTeacherTest.T5;
    SessionStudent 수강생 = 수강신청(강의, 수강생_유저);

    int changedStatusCount = sut.refuseSession(강의, 강사, 수강생);

    Assertions.assertThat(changedStatusCount)
        .isEqualTo(1);
  }

  private SessionStudent 수강신청(Session 강의, NsUser 수강생_유저) {
    강의.addPersonnel(수강생_유저);
    Long 수강생_id = sessionService.takeSession(수강생_유저, 강의.getId());
    return new SessionStudent(수강생_id, 강의, 수강생_유저.getId());
  }
}
