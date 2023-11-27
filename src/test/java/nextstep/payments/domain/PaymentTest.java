package nextstep.payments.domain;

import nextstep.courses.domain.type.Price;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentTest {

    @Test
    @DisplayName("결제 금액 비교를 할 수 있다")
    public void amount_comparison() {
        Payment payment = new Payment(0L, 0L, NsUserTest.JAVAJIGI.getId(), BigDecimal.valueOf(10_000));
        assertThat(payment.isEqualAmount(new Price(BigDecimal.valueOf(10_000)))).isTrue();
    }

}
