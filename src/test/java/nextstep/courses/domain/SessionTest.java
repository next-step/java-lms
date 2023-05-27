package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

  public static final Session S1 = new Session("tdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 1);
  public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name",
      "javajigi@slipp.net");
  public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name",
      "sanjigi@slipp.net");

  @DisplayName("강의는 종료일은 시작일보다 이전일 수 없다.")
  @Test
  public void throwException_ifEndDateBeforeStartDate() {
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.minusDays(3);
    assertThatThrownBy(() -> new Session("title", start, end, "img", SessionType.FREE, 10))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("강의는 최대 수강생 수는 1보다 작을 수 없다.")
  @Test
  public void throwException_ifLess0MaxRecruitment() {
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plusDays(3);
    assertThatThrownBy(() -> new Session("title", start, end, "img", SessionType.FREE, 0))
        .isInstanceOf(IllegalArgumentException.class);
  }

}
