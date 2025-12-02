package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionTest {
  private Session costSession;
  private Session freeSession;

  private final int MAX_CAPACITY = 300;
  private final int TUITION_FEE = 1000;
  private final int STUDENT_COUNT = 299;

  private static final SessionCoverImage COVER_IMAGE = new SessionCoverImage(300, 200, "png", 1024 * 500);

  @BeforeEach
  void init(){
    costSession = new Session(new Course("TDD", 1L), COVER_IMAGE, "2025-01-01", "2025-01-31", MAX_CAPACITY, TUITION_FEE, STUDENT_COUNT);
    costSession = costSession.openEnrollment();
    freeSession = new Session(new Course("TDD", 1L), COVER_IMAGE, "2025-01-01", "2025-01-31");
    freeSession = freeSession.openEnrollment();
  }

  // 금액
  @Test
  void 유료강의_최대수강인원_초과하면_예외(){
    costSession = costSession.enroll(TUITION_FEE);

    assertThatThrownBy(() -> costSession.enroll(TUITION_FEE))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
  }

  @Test
  void 유료강의_결재금액과_수강료가_동일하지_않으면_예외(){
    assertThatThrownBy(() -> costSession.enroll(TUITION_FEE - 1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
  }

  @Test
  void 유료강의_최대수강인원이하_결재금액과수강료동일(){
    assertDoesNotThrow(() -> costSession.enroll(TUITION_FEE));
  }

  @Test
  void 무료강의_최대수강인원제한_없음(){
    Session free = new Session(new Course("TDD", 1L), COVER_IMAGE, "2025-01-01", "2025-01-31");
    free = free.openEnrollment();

    for (int i = 0; i < 1000; i++) {
      free = free.enroll();
    }
  }

  @Test
  void 무료강의_수강료_0원(){
    assertDoesNotThrow(() -> freeSession.enroll(0));
  }


  //상태
  @Test
  void 강의상태_준비중_모집중_종료순으로_변화(){
    Session session = new Session(new Course("TDD", 1L), COVER_IMAGE, "2025-01-01", "2025-01-31");

    assertThat(session.getState()).isEqualTo(RecruitmentState.PREPARING);

    session = session.openEnrollment();
    assertThat(session.getState()).isEqualTo(RecruitmentState.RECRUITING);

    session = session.closeEnrollment();
    assertThat(session.getState()).isEqualTo(RecruitmentState.CLOSED);
  }

  @Test
  void 강의상태_모집중에만_수강신청가능(){
    assertDoesNotThrow(() -> costSession.enroll(TUITION_FEE));
  }

  @Test
  void 강의상태_그외_수강신청하면_예외(){
    Session preparing = new Session(new Course("TDD", 1L), COVER_IMAGE, "2025-01-01", "2025-01-31");

    assertThatThrownBy(() -> preparing.enroll(0))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("모집중인 강의만 수강신청이 가능합니다.");
  }

}