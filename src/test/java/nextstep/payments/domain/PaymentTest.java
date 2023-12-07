package nextstep.payments.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PaymentTest {
    @DisplayName("금액이 다른지 확인")
    @ParameterizedTest
    @CsvSource(value = {"800_000:false", "800_001:true", "799_999:true", "0:true"}, delimiter = ':')
    void 같은_금액인지_확인(Long amount, boolean expected) {
        Payment payment = new Payment("1", 1L, 1L, 800_000L);
        Assertions.assertThat(payment.isDifferentAmount(amount)).isEqualTo(expected);
    }
}