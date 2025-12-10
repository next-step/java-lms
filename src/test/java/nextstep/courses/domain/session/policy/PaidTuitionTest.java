package nextstep.courses.domain.session.policy;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.policy.tuition.PaidTuition;
import org.junit.jupiter.api.Test;

class PaidTuitionTest {

  @Test
  void 결제금액과_수강료가_동일하지_않으면_예외() {
    PaidTuition type = new PaidTuition(1000);

    assertThatThrownBy(() -> type.validate(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
  }

  @Test
  void 결제금액과_수강료가_동일하면_성공() {
    PaidTuition type = new PaidTuition(1000);

    assertThatCode(() -> type.validate(1000))
        .doesNotThrowAnyException();
  }
}