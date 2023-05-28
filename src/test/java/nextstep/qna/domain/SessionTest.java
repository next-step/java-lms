package nextstep.qna.domain;

import config.BaseTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionPayType;
import nextstep.courses.domain.session.SessionStudent;
import nextstep.courses.domain.session.SessionStudents;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest extends BaseTest {

  public static final Session S1 = new Session(1L, CourseTest.C1,
      SessionPayType.PAID,
      SessionStatus.RECRUITING,
      1,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  public static final Session S2 = new Session(2L, CourseTest.C1,
      SessionPayType.PAID,
      SessionStatus.PREPARING,
      1,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  public static final Session S3 = new Session(3L, CourseTest.C1,
      SessionPayType.PAID,
      SessionStatus.RECRUITING,
      3,
      LocalDateTime.of(2023, 5, 26, 13, 0),
      LocalDateTime.of(2023, 5, 28, 13, 0)
  );

  NsUser user1;
  NsUser user2;
  Session session1;
  Session session2;

  @BeforeEach
  void setup() {
    user1 = NsUserTest.JAVAJIGI;
    user2 = NsUserTest.SANJIGI;
    session1 = new Session(S1, getEmptyStudents());
    session2 = new Session(S2, getEmptyStudents());
  }

  @Test
  @DisplayName("강의 수강신청 | 강의는 강의 최대 수강 인원을 초과할 수 없다.")
  void 강의는_최대_수강_인원을_초과할_수_없다() {
    // given
    session1.addPersonnel(user1);

    // when & then
    super.assertThatThrowsLmsException(
        () -> session1.addPersonnel(user2),
        SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT
    );
  }

  @Test
  @DisplayName("강의 수강신청 | 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. (모집중 아닐때)")
  void 강의가_모집중이_아니면_수강신청을_할_수_없다_실패() {
    // given
    final Session 준비중인_강의 = new Session(S2, getEmptyStudents());

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
    final Session 모집중인_강의 = new Session(S1, getEmptyStudents());
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
