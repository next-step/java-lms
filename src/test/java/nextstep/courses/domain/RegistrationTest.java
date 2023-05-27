package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import nextstep.courses.DuplicatedException;
import nextstep.courses.RegistrationFulledException;
import nextstep.courses.RegistrationNotOpenedException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationTest {

  public final Session S1 = new Session("tdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(2), "tdd-img", SessionType.PAID, 1);
  public final Session S2 = new Session("atdd", LocalDateTime.now(),
      LocalDateTime.now().plusMonths(1), "atdd-img", SessionType.PAID, 30);
  public final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name",
      "javajigi@slipp.net");
  public final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name",
      "sanjigi@slipp.net");

  @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
  @Test
  public void createRegistration_throwException_ifSessionStatusNotRECRUITMENT() {
    S1.registerClose();

    assertThatThrownBy(() -> Registration.createRegistration(JAVAJIGI, S1)).isInstanceOf(
        RegistrationNotOpenedException.class);
  }

  @DisplayName("강의 수강신청 시 최대인원이 넘어가면 예외를 던진다.")
  @Test
  public void createRegistration_throwException_ifRegistrationFull() {
    S1.registerOpen();
    Registration.createRegistration(JAVAJIGI, S1);

    assertThatThrownBy(() -> Registration.createRegistration(SANJIGI, S1)).isInstanceOf(
        RegistrationFulledException.class);
  }

  @DisplayName("강의 수강신청 시 사용자는 강의를 중복신청할 수 없다.")
  @Test
  public void createRegistration_throwException_ifUserAlreadyRegisterSession() {
    S2.registerOpen();
    Registration.createRegistration(JAVAJIGI, S2);

    assertThatThrownBy(() -> Registration.createRegistration(JAVAJIGI, S2)).isInstanceOf(
        DuplicatedException.class);
  }

  @DisplayName("사용자는 강의 수강신청 취소 후 다시 신청할 수 있다.")
  @Test
  public void createRegistration_AfterCancel() {
    S1.registerOpen();
    Registration registration = Registration.createRegistration(JAVAJIGI, S1);
    registration.cancel();

    assertAll(
        () -> assertThat(S1.hasNsUser(JAVAJIGI)).isFalse(),
        () -> Registration.createRegistration(JAVAJIGI, S1),
        () -> assertThat(S1.hasNsUser(JAVAJIGI)).isTrue()
    );
  }
}
