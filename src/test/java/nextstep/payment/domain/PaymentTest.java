package nextstep.payment.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

	@DisplayName("결제를 한다.")
	@Test
	void createPayment() {
		// given&when
		Payment payment = new Payment("1", 1L, 1L, 100L);
		// then
		assertThat(payment.getAmount()).isEqualTo(100L);
	}
	@DisplayName("결제를 완료한 결제 정보는 payments에 저장한다.")
	@Test
	void createPayments() {
		// given
		Payment payment1 = new Payment("1", 1L, 1L, 100L);
		Payment payment2 = new Payment("1", 1L, 1L, 100L);

		// when
		Payments payments = new Payments();
		payments.add(payment1);
		payments.add(payment2);

		// then
		assertThat(payments.size()).isEqualTo(2);
	}
}
