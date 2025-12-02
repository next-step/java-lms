package nextstep.courses.domain.session.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.SessionType;
import org.junit.jupiter.api.Test;

class FreeTypeTest {

  @Test
  void 수강인원_제한없음() {
    SessionType type = new FreeType();

    for (int i = 0; i < 1000; i++) {
      type = type.enroll(0);
    }

    assertThat(type).isInstanceOf(FreeType.class);
  }

  @Test
  void 금액_상관없이_수강가능() {
    SessionType type = new FreeType();

    assertThatCode(() -> type.enroll(0))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.enroll(100))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.enroll(999999))
        .doesNotThrowAnyException();
  }
}