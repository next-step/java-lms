package nextstep.courses.domain.session.policy;

import static org.assertj.core.api.Assertions.assertThatCode;

import nextstep.courses.domain.session.policy.tuition.FreeTuition;
import nextstep.courses.domain.session.policy.tuition.TuitionPolicy;
import org.junit.jupiter.api.Test;

class FreeTuitionTest {

  @Test
  void 금액_상관없이_수강가능() {
    TuitionPolicy type = new FreeTuition();

    assertThatCode(() -> type.validate(0))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.validate(100))
        .doesNotThrowAnyException();
    assertThatCode(() -> type.validate(999999))
        .doesNotThrowAnyException();
  }
}