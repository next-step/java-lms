package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.session.CannotApplySession;
import org.junit.jupiter.api.Test;

class SessionTest {

  Session s1 = new Session(Status.RECRUITING, 5, 0);
  Session s2 = new Session(Status.RECRUITING, 5, 5);
  Session s3 = new Session(Status.END, 5, 0);

  @Test
  void 강의_수강신청은_상태가_모집중일_때만_가능하다() {
    assertThatThrownBy(() -> s3.apply())
        .isInstanceOf(CannotApplySession.class);
  }

  @Test
  void 강의는_최대_수강_인원을_초과할_수_없다(){
    assertThatThrownBy(() -> {
      s2.apply();
    }).isInstanceOf(CannotApplySession.class);
  }

}