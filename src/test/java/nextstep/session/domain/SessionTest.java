package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.session.CannotApplySession;
import nextstep.session.CannotMakeSession;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {

  Session s1 = new Session(new SessionApply(Status.RECRUITING, 5));
  Session s2 = new Session(new SessionApply(Status.RECRUITING, 1));
  Session s3 = new Session(new SessionApply(Status.END, 5));

  @Test
  void 강의_시작일은_강의_종료일보다_늦을_수_없다() {
    assertThatThrownBy(() -> new Session(
        new SessionDate(LocalDateTime.now(), LocalDateTime.now().minusDays(1))))
        .isInstanceOf(CannotMakeSession.class);
  }

  @Test
  void 강의_수강신청은_상태가_모집중일_때만_가능하다() {
    assertThatThrownBy(() -> s3.apply(NsUserTest.JAVAJIGI))
        .isInstanceOf(CannotApplySession.class);
  }

  @Test
  void 강의는_최대_수강_인원을_초과할_수_없다(){
      s2.apply(NsUserTest.SANJIGI);
    assertThatThrownBy(() -> {
      s2.apply(NsUserTest.JAVAJIGI);
    }).isInstanceOf(CannotApplySession.class);
  }

  void 강의를_수강신청할_수_있다(){
    s1.apply(NsUserTest.JAVAJIGI);
  }

  @Test
  void 중복으로_수강신청할_수_없다() {
    강의를_수강신청할_수_있다();
    assertThatThrownBy(() -> {
      s1.apply(NsUserTest.JAVAJIGI);
    }).isInstanceOf(CannotApplySession.class);
  }

}