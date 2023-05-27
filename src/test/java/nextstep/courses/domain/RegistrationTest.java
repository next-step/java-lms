package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationTest {

  public static final Session S1 = new Session("tdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 1);
  public static final Session S2 = new Session("atdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(1), "atdd-img", SessionType.PAID, 30);
  public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name",
      "javajigi@slipp.net");
  public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name",
      "sanjigi@slipp.net");

  @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
  @Test
  public void createRegistration_throwException_ifSessionStatusNotRECRUITMENT() {
    assertThatThrownBy(() -> Registration.createRegistration(S1, JAVAJIGI)).isInstanceOf(
        RegistrationNotOpenedException.class);
  }

  @DisplayName("강의 수강 최대인원이 넘어가면 예외를 던진다.")
  @Test
  public void createRegistration_throwException_ifRegistrationFull() {
    S1.registerOpen();
    Registration.createRegistration(S1, JAVAJIGI);

    assertThatThrownBy(() -> Registration.createRegistration(S1, SANJIGI)).isInstanceOf(
        RegistrationFulledException.class);
  }

  @DisplayName("사용자는 강의를 중복신청할 수 없다.")
  @Test
  public void createRegistration_throwException_ifUserAlreadyRegisterSession() {
    S2.registerOpen();
    Registration.createRegistration(S2, JAVAJIGI);

    assertThatThrownBy(() -> Registration.createRegistration(S2, JAVAJIGI)).isInstanceOf(
        DuplicatedException.class);
  }
}
