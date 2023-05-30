package nextstep.courses.domain;

import java.util.ArrayList;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AssertionUtils;

public class SessionLegacyTest {

  NsUser user1;
  NsUser user2;
  Session session1;

  @BeforeEach
  void setup() {
    user1 = NsUserTest.JAVAJIGI;
    user2 = NsUserTest.SANJIGI;
    session1 = new Session(SessionTest.S1, getEmptyStudents());
  }

  @Test
  @DisplayName("강의 수강신청 | 강의는 강의 최대 수강 인원을 초과할 수 없다.")
  @Deprecated
  void 강의는_최대_수강_인원을_초과할_수_없다() {
    // given
    session1.addPersonnel(user1);

    // when & then
    AssertionUtils.assertThatThrowsLmsException(
        () -> session1.addPersonnel(user2),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강의 수강신청 | 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. (모집중 일때)")
  @Deprecated
  void 강의가_모집중이_아니면_수강신청을_할_수_없다_성공() {
    // given
    final Session 모집중인_강의 = new Session(SessionTest.S1, getEmptyStudents());
    // when
    모집중인_강의.addPersonnel(user2);

    // then
    Assertions.assertThat(모집중인_강의.getMaxCapacity())
        .isEqualTo(1);
  }

  private SessionStudents getEmptyStudents() {
    return new SessionStudents(new ArrayList<>());
  }
}
