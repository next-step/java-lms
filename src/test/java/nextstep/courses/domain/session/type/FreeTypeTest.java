package nextstep.courses.domain.session.type;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class FreeTypeTest {

  @Test
  void 금액_상관없이_수강가능() {
    SessionType type = new FreeType();

    assertThatCode(() -> type.validateEnroll(0))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.validateEnroll(100))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.validateEnroll(999999))
        .doesNotThrowAnyException();
  }
}