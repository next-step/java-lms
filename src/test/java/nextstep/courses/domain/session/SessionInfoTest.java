package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static nextstep.courses.domain.session.SessionEnrollmentInfo.INVALID_STUDENT_COUNT;
import static nextstep.courses.domain.session.SessionInfo.SESSION_TITLE_IS_INCORRECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionInfoTest {

  @Test
  @DisplayName("정상 강의 명, 강의 상태, 무료 여부, 최대 학생수를 입력한 경우" +
      "SessionInfo 생성 테스트")
  void sessionInfo_create_test() {
    String title = "강의명";
    SessionStatus sessionStatus = SessionStatus.READY;
    boolean isFree = true;

    SessionInfo sessionInfo = SessionInfo.newFreeSession(title, sessionStatus);
    assertThat(sessionInfo.getSessionTitle()).isEqualTo(title);
    assertThat(sessionInfo.getSessionStatus()).isEqualTo(sessionStatus);
    assertThat(sessionInfo.getIsFree()).isEqualTo(isFree);
    assertThat(sessionInfo.getStudentMaxCount()).isEqualTo(Integer.MAX_VALUE);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("강의명이 null 또는 empty인 경우" +
      "exception 테스트")
  void sessionInfo_fail_test_by_incorrect_title(String title) {
    SessionStatus sessionStatus = SessionStatus.READY;

    assertThatThrownBy(() -> SessionInfo.newFreeSession(title, sessionStatus))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(SESSION_TITLE_IS_INCORRECT, title));
  }

  @Test
  @DisplayName("유로 강의인데 수강 인원이 0인 경우" +
      "exception 테스트")
  void sessionInfo_fail_test_by_invalid_student_count() {
    String title = "강의명";
    SessionStatus sessionStatus = SessionStatus.READY;
    int sessionAmount = 0;
    int studentMaxCount = 0;

    assertThatThrownBy(() -> SessionInfo.newPaidSession(title, sessionStatus, sessionAmount, studentMaxCount))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_STUDENT_COUNT, studentMaxCount));
  }
}
