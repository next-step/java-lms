package nextstep.courses.domain;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ChargedSessionTest {
  private NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
  private NsUser SANGJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
  private NsUser CJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
  private NsUser PYTHONJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
  private SessionImage IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE");

  @BeforeEach
  void beforeEach() {
    JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    SANGJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    CJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    PYTHONJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    IMAGE = new SessionImage(1L, 300, 200, "jpg", 1024, "TEST_IMAGE");
  }

  @ParameterizedTest
  @ValueSource(strings = { "PREPARING", "CLOSED" })
  void 모집중이지_않음(SessionStatus status) {
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), IMAGE, status,
            5, 10000L, List.of());
    assertThatThrownBy(() -> session.addStudent(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강생 모집중인 강의가 아닙니다.");
  }

  @Test
  void 최대_수강인원_초과() {
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), IMAGE, SessionStatus.OPEN,
            3, 10000L, List.of(SANGJIGI, CJIGI, PYTHONJIGI));
    assertThatThrownBy(() -> session.addStudent(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("최대 수강인원을 초과하였습니다.");
  }

  @ParameterizedTest
  @ValueSource(longs = { 5000L, 15000L })
  void 결제_정보_일치하지_않음(Long input) {
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), IMAGE, SessionStatus.OPEN,
            5, 10000L, List.of(SANGJIGI, CJIGI, PYTHONJIGI));
    JAVAJIGI.addPayment(new Payment("TEST_PAYMENT", 1L, 1L, input));
    assertThatThrownBy(() -> session.addStudent(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("일치하는 결제 정보가 없습니다.");
  }

  @Test
  void 정상_수강_신청() {
    ChargedSession session = new ChargedSession(1L, 1L, LocalDate.now(), LocalDate.now().plusMonths(1L), IMAGE, SessionStatus.OPEN,
            3, 10000L, List.of(SANGJIGI));
    JAVAJIGI.addPayment(new Payment("TEST_PAYMENT", 1L, 1L, 10000L));
    session.addStudent(JAVAJIGI);
    assertThat(session.numberOfStudents()).isEqualTo(2);
  }
}
