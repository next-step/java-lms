package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPayType;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitStatus;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.qna.domain.CourseTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AssertionUtils;

public class SessionTest {

  @Deprecated
  public static final Session S1 = new Session(1L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.RECRUITING,
      null,
      1,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  @Deprecated
  public static final Session S2 = new Session(2L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.PREPARING,
      null,
      1,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  @Deprecated
  public static final Session S3 = new Session(3L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.RECRUITING,
      null,
      3,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  public static final Session S4 = new Session(4L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.ONGOING,
      SessionRecruitStatus.RECRUIT,
      5,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  public static final Session S5 = new Session(5L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.ONGOING,
      SessionRecruitStatus.RECRUIT,
      1,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  public static final Session S6 = new Session(6L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.END,
      SessionRecruitStatus.RECRUIT,
      5,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  // 강사가 없는 강의
  public static final Session S7 = new Session(7L, CourseTest.C1,
      SessionPayType.PAID,
      SessionProgressStatus.END,
      SessionRecruitStatus.RECRUIT,
      5,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  NsUser user1;
  NsUser user2;

  @BeforeEach
  void setup() {
    user1 = NsUserTest.JAVAJIGI;
    user2 = NsUserTest.SANJIGI;
  }

  @Test
  @DisplayName("강의 수강신청 | 강의는 강의 최대 수강 인원을 초과할 수 없다.")
  void 강의는_최대_수강_인원을_초과할_수_없다() {
    // given
    Session 최대수강인원_1명인_강의 = new Session(S5);
    최대수강인원_1명인_강의.addPersonnel(user1);

    // when & then
    AssertionUtils.assertThatThrowsLmsException(
        () -> 최대수강인원_1명인_강의.addPersonnel(user2),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강의 수강신청은 강의 상태가 진행상태가 진행중이고, 모집상태가 모집중이 아니면 불가능하다")
  void 강의_수강신청_불가능상태() {
    // given
    final Session 준비중인_강의 = new Session(S6);

    // when & then
    AssertionUtils.assertThatThrowsLmsException(
        () -> 준비중인_강의.addPersonnel(user2),
        SessionExceptionCode.CANNOT_ENROLL_SESSION
    );
  }

  @Test
  @DisplayName("강의 수강신청은 강의 상태가 진행상태가 진행중이고, 모집상태가 모집중 일 때만 가능하다.")
  void 강의_수강신청_가능상태() {
    // given
    final Session 모집중인_강의 = new Session(S5);
    // when
    모집중인_강의.addPersonnel(user2);

    // then
    Assertions.assertThat(모집중인_강의.getMaxCapacity())
        .isEqualTo(1);
  }
}
