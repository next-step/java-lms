package nextstep.courses.domain.session.type;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.type.PaidType;
import org.junit.jupiter.api.Test;

class PaidTypeTest {

  @Test
  void 최대수강인원_초과하면_예외() {
    PaidType type = new PaidType(300, 1000, 300);

    assertThatThrownBy(() -> type.enroll(1000))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
  }

  @Test
  void 결제금액과_수강료가_동일하지_않으면_예외() {
    PaidType type = new PaidType(300, 1000);

    assertThatThrownBy(() -> type.enroll(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
  }

  @Test
  void 최대수강인원이하_결제금액과수강료동일하면_성공() {
    PaidType type = new PaidType(300, 1000);

    assertThatCode(() -> type.enroll(1000))
        .doesNotThrowAnyException();
  }
}