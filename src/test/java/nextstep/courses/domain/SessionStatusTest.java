package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {

  @ParameterizedTest(name = "수강신청 상태 확인")
  @MethodSource("generateStatusStatusData")
  public void check_수강신청(SessionStatus sessionStatus, boolean expected) {
    assertThat(sessionStatus.canEnroll()).isEqualTo(expected);
  }

  private static Stream<Arguments> generateStatusStatusData() {
    return Stream.of(
            Arguments.of(new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING), false),
            Arguments.of(new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING), false),
            Arguments.of(new SessionStatus(SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING), true),
            Arguments.of(new SessionStatus(SessionProgressStatus.IN_PROGRESSING, SessionRecruitmentStatus.RECRUITING), true),
            Arguments.of(new SessionStatus(SessionProgressStatus.IN_PROGRESSING, SessionRecruitmentStatus.NOT_RECRUITING), false),
            Arguments.of(new SessionStatus(SessionProgressStatus.ENDING, SessionRecruitmentStatus.RECRUITING), false),
            Arguments.of(new SessionStatus(SessionProgressStatus.ENDING, SessionRecruitmentStatus.NOT_RECRUITING), false)
    );
  }
}
