package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    public static Payment paymentOneThousand() {
        return new Payment("id", 1L,1L,1000L);
    }

    @Test
    @DisplayName("성공 - 결제 금액과 가격이 같을 경우 true를 반환한다.")
    void success_payment_price_equal_price() {
        assertThat(paymentOneThousand().isSameBy(1000L)).isTrue();
    }
}
