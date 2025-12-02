package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class SessionTest {
  private static final SessionCoverImage COVER_IMAGE = new SessionCoverImage(300, 200, "png", 1024 * 500);
  private static final int TERM = 19;

  @Test
  void 모집중일때_수강신청_가능() {
    Session session = new Session(new Course("TDD", 1L), TERM, COVER_IMAGE, "2025-01-01", "2025-01-31");
    Session recruiting = session.openEnrollment();

    assertDoesNotThrow(() -> recruiting.enroll(0));
  }

  @Test
  void 모집중이_아닐때_수강신청하면_예외() {
    Session preparing = new Session(new Course("TDD", 1L), TERM, COVER_IMAGE, "2025-01-01", "2025-01-31");

    assertThatThrownBy(() -> preparing.enroll(0))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 수강신청이 가능합니다.");
  }

  @Test
  void 준비중이_아닐때_모집시작하면_예외() {
    Session session = new Session(new Course("TDD", 1L), TERM, COVER_IMAGE, "2025-01-01", "2025-01-31");
    Session recruiting = session.openEnrollment();

    assertThatThrownBy(() -> recruiting.openEnrollment())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("준비중인 강의만 모집을 시작할 수 있습니다.");
  }

  @Test
  void 모집중이_아닐때_종료하면_예외() {
    Session preparing = new Session(new Course("TDD", 1L), TERM, COVER_IMAGE, "2025-01-01", "2025-01-31");

    assertThatThrownBy(() -> preparing.closeEnrollment())
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 종료할 수 있습니다.");
  }
}