package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionTest {

  Session session;
  NsUser user;

  @BeforeEach
  void setup() {
    session = new Session(
        "제목",
        "내용",
        LocalDateTime.of(2023, 6, 2, 12, 0),
        LocalDateTime.of(2023, 6, 3, 0, 0),
        null,
        1);
    user = NsUserTest.JAVAJIGI;
  }

  @Test
  void 생성자_수강신청_날짜_검증_실패() {
    session = new Session(
        "제목",
        "내용",
        LocalDateTime.of(2023, 6, 3, 0, 0),
        LocalDateTime.of(2023, 6, 2, 0, 0),
        null,
        1);

    assertThatThrownBy(() -> session.validate())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강신청 종료 일자는 수강신청 시작 일자보다 빠를 수 없습니다");
  }

  @Test
  void 생성자_수강신청_인원_검증_실패() {
    session = new Session(
        "제목",
        "내용",
        LocalDateTime.of(2023, 6, 2, 0, 0),
        LocalDateTime.of(2023, 6, 3, 0, 0),
        null,
        0);

    assertThatThrownBy(() -> session.validate())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강인원 수는 1명 이상이어야 합니다");
  }

  @Test
  void enrolment_성공() {
    session.recruitStart();
    session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 0));

    assertThat(session.getUsers()).containsExactly(user);
  }

  @Test
  void enrollment_실패_모집중_아님() {
    assertThatThrownBy(() -> session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 0)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 신청 가능합니다");

    session.recruitEnd();

    assertThatThrownBy(() -> session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 0)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 신청 가능합니다");
  }

  @Test
  void enrollment_실패_모집인원_초과() {
    session = new Session(
        "제목",
        "내용",
        LocalDateTime.of(2023, 6, 2, 12, 0),
        LocalDateTime.of(2023, 6, 3, 0, 0),
        null,
        1);

    session.recruitStart();
    session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 1));

    assertThatThrownBy(
        () -> session.enrollment(NsUserTest.SANJIGI, LocalDateTime.of(2023, 6, 2, 12, 1)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강인원이 초과되었습니다");
  }

  @Test
  void enrollment_실패_이미_신청한_사용자() {
    session = new Session(
        "제목",
        "내용",
        LocalDateTime.of(2023, 6, 2, 12, 0),
        LocalDateTime.of(2023, 6, 3, 0, 0),
        null,
        2);

    session.recruitStart();
    session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 0));

    assertThatThrownBy(() -> session.enrollment(user, LocalDateTime.of(2023, 6, 2, 12, 0)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("이미 수강신청한 사용자입니다");
  }

  @Test
  void enrollment_실패_수강신청_기간_아님() {
    assertThatThrownBy(() -> session.enrollment(user, LocalDateTime.of(2023, 6, 2, 11, 59)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강신청 기간이 아닙니다");
    assertThatThrownBy(() -> session.enrollment(user, LocalDateTime.of(2023, 6, 3, 12, 00)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강신청 기간이 아닙니다");
  }
}