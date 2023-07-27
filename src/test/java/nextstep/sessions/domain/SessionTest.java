package nextstep.sessions.domain;

import static nextstep.sessions.testFixture.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.sessions.domain.students.Student;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionTest {

  Session session;
  NsUser user;

  @BeforeEach
  void setup() {
    this.session = aSession().build();
    this.user = NsUserTest.JAVAJIGI;
  }

  @Test
  void 생성자_수강신청_날짜_검증_실패() {
    this.session = aSession()
        .withStartDate(LocalDateTime.of(2023, 6, 3, 0, 0))
        .withEndDate(LocalDateTime.of(2023, 6, 2, 0, 0))
        .build();

    assertThatThrownBy(() -> this.session.validateInit())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강신청 종료 일자는 수강신청 시작 일자보다 빠를 수 없습니다");
  }

  @Test
  void 생성자_수강신청_인원_검증_실패() {
    this.session = aSession()
        .withCapacity(0)
        .build();

    assertThatThrownBy(() -> this.session.validateInit())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강인원 수는 1명 이상이어야 합니다");
  }

  @Test
  void enrolment_성공() {
    this.session.recruitStart();
    Student student = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 2, 12, 0), null);
    this.session.enrollment(this.session.getStudents(), student);

    Student stu = this.session.studentsAsSet()
        .stream().filter(
            s -> s.getSessionId().equals(this.session.getId()) && s.getNsUserId()
                .equals(s.getNsUserId()))
        .findFirst()
        .orElseThrow();

    assertThat(stu).isNotNull();
  }

  @Test
  void enrollment_실패_모집중_아님() {
    Student student = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 2, 12, 0), null);

    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 신청 가능합니다");

    this.session.recruitEnd();

    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 신청 가능합니다");
  }

  @Test
  void enrollment_실패_모집인원_초과() {
    this.session.recruitStart();
    Student student1 = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 2, 12, 0), null);
    Student student2 = new Student(this.session, NsUserTest.SANJIGI, LocalDateTime.of(2023, 6, 2, 12, 1), null);
    this.session.enrollment(this.session.getStudents(), student1);

    assertThatThrownBy(
        () -> this.session.enrollment(this.session.getStudents(), student2))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강인원이 초과되었습니다");
  }

  @Test
  void enrollment_실패_이미_신청한_사용자() {
    this.session = aSession()
        .withCapacity(2)
        .build();

    this.session.recruitStart();
    Student student = new Student(this.session, user, LocalDateTime.of(2023, 6, 2, 12, 0), null);
    this.session.enrollment(this.session.getStudents(), student);

    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("이미 수강신청한 사용자입니다");
  }

  @Test
  void enrollment_실패_수강신청_기간_아님() {
    Student student1 = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 2, 11, 59), null);
    Student student2 = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 3, 12, 00), null);

    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student1))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강신청 기간이 아닙니다");
    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student2))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("수강신청 기간이 아닙니다");
  }

  @Test
  void enrollment_실패_강의_종료() {
    this.session = aSession()
        .withSessionProgressStatus(SessionProgressStatus.END)
        .withSessionRecruitingStatus(SessionRecruitingStatus.RECRUITING)
        .build();
    Student student = new Student(this.session, this.user, LocalDateTime.of(2023, 6, 2, 12, 0), null);

    assertThatThrownBy(() -> this.session.enrollment(this.session.getStudents(), student))
        .isInstanceOf(IllegalStateException.class)
            .hasMessage("강의가 종료된 상태입니다");
  }
}