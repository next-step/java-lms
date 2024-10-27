package nextstep.payment;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    @DisplayName("결재 내역을 생성한다.")
    @Test
    void createPaymentTest() {
        //given, when
        Payment payment = new Payment(1L, 1L, 1L, 800000);

        //then
        assertThat(payment)
                .extracting("id", "sessionId", "nsUserId", "amount")
                .contains(1L, 1L, 1L, 800000);
    }
}
