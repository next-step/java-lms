package nextstep.qna.domain;

import config.BaseTest;
import java.time.LocalDateTime;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest extends BaseTest {

  NsUser user1;
  NsUser user2;

  public static final Session S1 = new Session(1L, CourseTest.C1, SessionCoverImageTest.IMG1,
      SessionType.PAID,
      SessionStatus.RECRUITING,
      1,
      LocalDateTime.of(2023, 1, 1, 12, 00),
      LocalDateTime.of(2023, 1, 1, 13, 00)
  );

  public static final Session S2 = new Session(1L, CourseTest.C1, SessionCoverImageTest.IMG1,
      SessionType.PAID,
      SessionStatus.PREPARING,
      1,
      LocalDateTime.of(2023, 1, 1, 12, 00),
      LocalDateTime.of(2023, 1, 1, 13, 00)
  );

  @BeforeEach
  void setup() {
    user1 = NsUserTest.JAVAJIGI;
    user2 = NsUserTest.SANJIGI;
  }

  @Test
  @DisplayName("강의 수강신청 | 강의는 강의 최대 수강 인원을 초과할 수 없다.")
  void 강의는_최대_수강_인원을_초과할_수_없다() {
    // given
    S1.addPersonnel(user1);

    // when & then
    super.assertThatThrowsLmsException(
        () -> S1.addPersonnel(user2),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강의 수강신청 | 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. (모집중 아닐때)")
  void 강의가_모집중이_아니면_수강신청을_할_수_없다_실패() {
    // given
    final Session 준비중인_강의 = new Session(1L, CourseTest.C1, SessionCoverImageTest.IMG1,
        SessionType.PAID,
        SessionStatus.PREPARING,
        1,
        LocalDateTime.of(2023, 1, 1, 12, 00),
        LocalDateTime.of(2023, 1, 1, 13, 00)
    );

    // when & then
    super.assertThatThrowsLmsException(
        () -> 준비중인_강의.addPersonnel(user2),
        SessionExceptionCode.ONLY_RECRUITING_STATUS_ALLOWED
    );
  }

  @Test
  @DisplayName("강의 수강신청 | 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. (모집중 일때)")
  void 강의가_모집중이_아니면_수강신청을_할_수_없다_성공() {
    // given
    final Session 모집중인_강의 = new Session(1L, CourseTest.C1, SessionCoverImageTest.IMG1,
        SessionType.PAID,
        SessionStatus.RECRUITING,
        1,
        LocalDateTime.of(2023, 1, 1, 12, 00),
        LocalDateTime.of(2023, 1, 1, 13, 00)
    );

    // when
    모집중인_강의.addPersonnel(user2);

    // then
    Assertions.assertThat(모집중인_강의.getPersonnelSize())
        .isEqualTo(1);
  }
}
