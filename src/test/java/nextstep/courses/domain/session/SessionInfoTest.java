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
    String given1 = "강의명";
    SessionStatus given2 = SessionStatus.READY;
    boolean given3 = true;

    SessionInfo sessionInfo = new SessionInfo(given1, given2, given3, 0L, null);
    assertThat(sessionInfo.getSessionTitle()).isEqualTo(given1);
    assertThat(sessionInfo.getSessionStatus()).isEqualTo(given2);
    assertThat(sessionInfo.getIsFree()).isEqualTo(given3);
    assertThat(sessionInfo.getStudentMaxCount()).isEqualTo(null);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("강의명이 null 또는 empty인 경우" +
      "exception 테스트")
  void sessionInfo_fail_test_by_incorrect_title(String given) {
    SessionStatus given2 = SessionStatus.READY;
    boolean given3 = true;

    assertThatThrownBy(() -> new SessionInfo(given, given2, given3, 0L, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(SESSION_TITLE_IS_INCORRECT, given));
  }

  @Test
  @DisplayName("유로 강의인데 수강 인원이 0인 경우" +
      "exception 테스트")
  void sessionInfo_fail_test_by_invalid_student_count() {
    String given1 = "강의명";
    SessionStatus given2 = SessionStatus.READY;
    boolean given3 = false;
    Long given4 = 0L;
    int given5 = 0;

    assertThatThrownBy(() -> new SessionInfo(given1, given2, given3, given4, given5))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_STUDENT_COUNT, given5));
  }
}
