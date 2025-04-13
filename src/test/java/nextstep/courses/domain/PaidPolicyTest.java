package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaidPolicyTest {
  @Test
  @DisplayName("정원이 초과되지 않고 결제 금액이 일치하면 수강 신청이 가능하다.")
  void validate_success() {
    PaidPolicy policy = new PaidPolicy(5, 10000L);
    Payment payment = new Payment("p1", 1L, 10L, 10000L);

    assertThatCode(() -> policy.validate(payment, 3))
        .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("정원이 초과되면 예외가 발생한다.")
  void validate_exceed_limit() {
    PaidPolicy policy = new PaidPolicy(3, 10000L);
    Payment payment = new Payment("p2", 1L, 11L, 10000L);

    assertThatIllegalStateException()
        .isThrownBy(() -> policy.validate(payment, 3))
        .withMessageContaining("정원 초과");
  }

  @Test
  @DisplayName("결제 금액이 일치하지 않으면 예외가 발생한다.")
  void validate_invalid_payment() {
    PaidPolicy policy = new PaidPolicy(5, 10000L);
    Payment payment = new Payment("p3", 1L, 12L, 8000L);

    assertThatIllegalArgumentException()
        .isThrownBy(() -> policy.validate(payment, 2))
        .withMessageContaining("결제 금액");
  }

}
