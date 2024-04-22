package nextstep.payment.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.sessions.domain.ChargeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class PaymentTest {

	@DisplayName("결제를 한다.")
	@Test
	void createPayment() {
		// given&when
		Payment payment = new Payment("1", 1L, 1L, 100L,100L, ChargeStatus.PAID);
		// then
		assertThat(payment.getAmount()).isEqualTo(100L);
	}

	@DisplayName("결제금액과 강의금액이 다르면 결제가 되지 않는다.")
	@Test
	void createPaymentWithNotEqualAmount() {
		// given&when&then
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> {
					new Payment("1", 1L, 1L, 100L,101L, ChargeStatus.PAID);
				}).withMessageMatching("결제 금액이 강의 금액과 일치하지 않습니다.");
	}

	@DisplayName("결제를 완료한 결제 정보는 payments에 저장한다.")
	@Test
	void createPayments() {
		// given
		Payment payment1 = new Payment("1", 1L, 1L, 100L, 100L, ChargeStatus.PAID);
		Payment payment2 = new Payment("1", 1L, 1L, 100L, 100L, ChargeStatus.PAID);

		// when
		Payments payments = new Payments();
		payments.add(payment1);
		payments.add(payment2);

		// then
		assertThat(payments.size()).isEqualTo(2);
	}
}
