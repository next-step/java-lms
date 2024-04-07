package courses.domain;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
  public static NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
  public static SessionImage IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE");

  @BeforeEach
  void beforeEach() {
    JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE");
  }

  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않은_경우_수강신청(SessionStatus status) {
    FreeSession session = new FreeSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), IMAGE,
            status, List.of(JAVAJIGI));
    assertThatThrownBy(() -> session.addStudent(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강생 모집중인 강의가 아닙니다.");
  }

  @Test
  void 정상_수강신청() {
    FreeSession session = new FreeSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L),
            IMAGE, SessionStatus.OPEN, List.of());
    session.addStudent(JAVAJIGI);

    assertThat(session.numberOfStudents()).isEqualTo(1);
  }
}
